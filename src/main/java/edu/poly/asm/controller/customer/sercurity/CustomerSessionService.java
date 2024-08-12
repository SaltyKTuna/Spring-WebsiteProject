package edu.poly.asm.controller.customer.sercurity;

import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpSession;

@Service
public class CustomerSessionService {
    private final HttpSession session;

    public CustomerSessionService(HttpSession session) {
        this.session = session;
    }

    public <T> T get(String name) {
        return (T) session.getAttribute(name);
    }

    public void set(String name, Object value) {
        session.setAttribute(name, value);
    }

    public void remove(String name) {
        session.removeAttribute(name);
    }
    
    public void setCustomerId(int i) {
        session.setAttribute("customerId", i);
    }

    public Integer getCustomerId() {
        return (Integer) session.getAttribute("customerId");
    }
    
    public void removeID() {
        session.removeAttribute("customerId");
    }
}
