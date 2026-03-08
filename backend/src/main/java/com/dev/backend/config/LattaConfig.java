package com.dev.backend.config;

import ai.latta.spring.filters.LattaResponseFilter;
import ai.latta.spring.interceptors.LattaInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LattaConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LattaInterceptor("d4329e323981d7c5ebe8e79d688e24e1dc5c92f9dd74d27ae70c64d39b8220f55c0f16453a2bde07828a800b44c0460162542aaeb7caed6362db5a02198bd912"))
                .addPathPatterns("/**");
    }

    @Bean
    public FilterRegistrationBean<LattaResponseFilter> applyLattaResponseFilter() {
        FilterRegistrationBean<LattaResponseFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LattaResponseFilter());
        registrationBean.addUrlPatterns("/*"); // Apply to all requests
        return registrationBean;
    }
}
