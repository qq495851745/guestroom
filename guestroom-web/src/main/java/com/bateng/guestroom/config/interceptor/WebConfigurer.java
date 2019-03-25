package com.bateng.guestroom.config.interceptor;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfigurer extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> sessionInvalidLists = new ArrayList<String>();
        sessionInvalidLists.add("/index");
        sessionInvalidLists.add("/ajax/**");
        sessionInvalidLists.add("/option/**");
        sessionInvalidLists.add("/guestroom/**");
        registry.addInterceptor(new SessionInvalidInterceptor()).addPathPatterns(sessionInvalidLists);
    }
}
