package com.laptrinhjavaweb.config;

import com.laptrinhjavaweb.constant.SystemConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        Path buildingUploadDir = Paths.get(SystemConstants.UPLOAD_BUILDING_FILE_DIR);
        String buildingUploadPath = buildingUploadDir.toFile().getAbsolutePath();
        System.out.println(buildingUploadPath);
        /* For Window */
//        registry.addResourceHandler(SystemConstants.LOAD_FILE_DIR + "**").addResourceLocations("file:/" + buildingUploadPath + "/");
        /* For Linux */
        registry.addResourceHandler(SystemConstants.LOAD_FILE_DIR + "**").addResourceLocations("file://" + buildingUploadPath + "/");

    }
}
