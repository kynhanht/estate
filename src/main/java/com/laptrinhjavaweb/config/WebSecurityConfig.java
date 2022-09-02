package com.laptrinhjavaweb.config;

import com.laptrinhjavaweb.security.CustomAccessDeniedHandler;
import com.laptrinhjavaweb.security.CustomAuthenticationFailureHandler;
import com.laptrinhjavaweb.security.CustomAuthenticationSuccessHandler;
import com.laptrinhjavaweb.service.impl.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Configure authorization
        http.csrf().disable().authorizeRequests()
                .antMatchers("/admin/user-edit**").hasRole("MANAGER")
                .antMatchers("/admin/building-edit").hasRole("MANAGER")
                .antMatchers("/admin/customer-edit").hasRole("MANAGER")
                .antMatchers("/admin/**").hasAnyRole("MANAGER", "STAFF")
                .antMatchers("/login/**", "/web/**", "/errors", "/home").permitAll()
                .anyRequest().authenticated();
        // Configure remember me
        http.rememberMe()
                .rememberMeParameter("remember-me")
                .rememberMeCookieName("remember-me")
                .key("uniqueAndSecret")
                .tokenValiditySeconds(60 * 60 * 24 * 3);
        // Configure login form
        http.formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/spring_security_check")
                .successHandler(customAuthenticationSuccessHandler())
                .failureHandler(customAuthenticationFailureHandler())
                .and()
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler())
                .and()
                .sessionManagement().maximumSessions(1).expiredUrl("/login?expired");
        // Configure logout
        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .deleteCookies("JSESSIONID")
                .deleteCookies("remember-me");

    }

    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler customAuthenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    @Bean
    public AccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

}
