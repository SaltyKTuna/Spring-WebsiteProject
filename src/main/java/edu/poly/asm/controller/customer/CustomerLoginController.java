package edu.poly.asm.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import edu.poly.asm.controller.customer.sercurity.CustomerSessionService;
import edu.poly.asm.domain.Customer;
import edu.poly.asm.model.CustomerDto;
import edu.poly.asm.service.CustomerService;

@Controller
public class CustomerLoginController {
    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerSessionService customerSession;

    @GetMapping("/site/login")
    public String login() {
        return "site/login";
    }

    @PostMapping("/site/login")
    public String login(Model model, @RequestParam("name") String name,
                        @RequestParam("password") String password) {
        try {
            Customer customer = customerService.findByNameContaining(name);
            if (customer == null || !customer.getPassword().equals(password)) {
                model.addAttribute("message", "Invalid username or password!!");
                return "site/login";
            }

            customerSession.set("customer", customer);
            customerSession.setCustomerId(customer.getCustomerID());
            System.out.println("Session ID: "+ customerSession.getCustomerId());

            String uri = customerSession.get("security-uri");
            if (uri != null) {
                customerSession.remove("security-uri");
                return "redirect:" + uri;
            } else {
                model.addAttribute("message", "Login succeed!!");
                return "site/home";
            }
        } catch (Exception e) {
            model.addAttribute("message", "Invalid username or password!!");
            return "site/login";
        }
    }

    @GetMapping("/site/logout")
    public String logout(Model model) {
        customerSession.remove("customer");
        customerSession.removeID();
        model.addAttribute("message", "Logout Succeed!!");
        return "forward:/site/login";
    }

}
