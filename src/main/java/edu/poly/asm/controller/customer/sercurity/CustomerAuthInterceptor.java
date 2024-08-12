package edu.poly.asm.controller.customer.sercurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import edu.poly.asm.domain.Customer;
import edu.poly.asm.model.CustomerDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomerAuthInterceptor implements HandlerInterceptor {
    @Autowired
    CustomerSessionService customerSession;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String uri = request.getRequestURI();
        Customer customer = customerSession.get("customer");

        System.out.println(uri);

        if (customer == null) {
            String error = "You need to login first!!";
            customerSession.set("security-uri", uri);
            response.sendRedirect("/site/login?error=" + error);
            return false;
        }

        // Đặt customer vào request attribute để sử dụng trong Thymeleaf
        request.setAttribute("loggedInCustomer", customer);

        return true;
    }
}
