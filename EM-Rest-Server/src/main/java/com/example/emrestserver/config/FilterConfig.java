package com.example.emrestserver.config;

import com.example.emrestserver.security.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class FilterConfig {
    @Value("${services.auth.login}")
    private String authLoginUrl;

    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilter() {
        final FilterRegistrationBean<JwtFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new JwtFilter());
        filterFilterRegistrationBean.setInitParameters(Collections.singletonMap("authLoginUrl", authLoginUrl));
        // The difference between /* & /** is that the second matches the entire directory tree, including subdirectories, where as /* only matches at the level it's specified at.
        filterFilterRegistrationBean.addUrlPatterns("/em/*");
        filterFilterRegistrationBean.setOrder(1);
        return filterFilterRegistrationBean;
    }

}
