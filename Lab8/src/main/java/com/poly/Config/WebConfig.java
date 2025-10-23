package com.poly.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.poly.Interceptor.LogInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LogInterceptor logInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // áp dụng LogInterceptor cho tất cả URL
        registry.addInterceptor(logInterceptor)
                .addPathPatterns("/**"); // hoặc lọc theo path cần ghi log
    }
}