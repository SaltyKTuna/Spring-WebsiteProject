package edu.poly.asm.controller.customer.sercurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CustomerInterceptorConfig implements WebMvcConfigurer {
    @Autowired
    CustomerAuthInterceptor customerAuth;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customerAuth)
                .addPathPatterns("/site/home", "/site/cart/**")
                .excludePathPatterns("/site/login", "/register", "/public/**");
    }
}
