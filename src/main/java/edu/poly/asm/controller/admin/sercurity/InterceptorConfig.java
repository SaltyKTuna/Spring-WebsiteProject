package edu.poly.asm.controller.admin.sercurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    AuthInterceptor auth;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(auth)
                .addPathPatterns("/admin/accounts/**", "/admin/categories/**", "/admin/customers/**", "/admin/products/**", "/admin/home/**")
                .excludePathPatterns("/admin/login", "/register", "/public/**");
    }
}
