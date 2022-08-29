package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.constant.ErrorMessageConstants;
import com.laptrinhjavaweb.constant.SystemConstants;
import com.laptrinhjavaweb.dto.MyUserDetail;
import com.laptrinhjavaweb.entity.RoleEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.exception.NotFoundException;
import com.laptrinhjavaweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository
                .findOneByUserNameAndStatus(username, SystemConstants.ACTIVE_STATUS)
                .orElseThrow(() -> new NotFoundException(ErrorMessageConstants.USER_NOT_FOUND));

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (RoleEntity role : userEntity.getRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getCode()));
        }
        MyUserDetail myUserDetail = new MyUserDetail(username, userEntity.getPassword(), true, true, true, true, authorities);
        myUserDetail.setId(userEntity.getId());
        myUserDetail.setFullName(userEntity.getFullName());
        return myUserDetail;
    }
}
