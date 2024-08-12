package edu.poly.asm.controller.admin.sercurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import edu.poly.asm.model.AccountDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    SessionService session;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String uri = request.getRequestURI();
        Object userObject = session.get("user");

        System.out.println(uri);

        if (userObject == null || !(userObject instanceof AccountDto)) {
            String error = "You need to login first!!";
            session.set("security-uri", uri);
            response.sendRedirect("/admin/login?error=" + error);
            return false;
        }

        AccountDto user = (AccountDto) userObject;

        if ("sondt".equals(user.getUsername())) {
            user.setIsManager(true);
        }

        if (!user.getIsManager() && uri.startsWith("/admin/accounts")) {
            String error = "Access denied!!";
            session.set("security-uri", "/admin/home");
            response.sendRedirect("/admin/login?error=" + error);
            return false;
        }

        // Đặt user vào request attribute để sử dụng trong Thymeleaf
        request.setAttribute("loggedInUser", user);

        return true;
    }
}
