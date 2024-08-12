package edu.poly.asm.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.asm.controller.admin.sercurity.SessionService;
import edu.poly.asm.domain.Account;
import edu.poly.asm.model.AccountDto;
import edu.poly.asm.service.AccountService;

@Controller
public class LoginController {
    @Autowired
    AccountService accountService;

    @Autowired
    SessionService session;

    @GetMapping("/admin/login")
    public String login() {
        return "admin/login";
    }

    @PostMapping("/admin/login")
    public String login(Model model, @RequestParam("username") String username,
                        @RequestParam("password") String password) {
        try {
            Account user = accountService.getOne(username);
            if (user == null || !user.getPassword().equals(password)) {
                model.addAttribute("message", "Invalid username or password!!");
                return "admin/login";
            }

            AccountDto userDto = new AccountDto();
            userDto.setUsername(user.getUsername());
            session.set("user", userDto);

            String uri = session.get("security-uri");
            if (uri != null) {
                session.remove("security-uri");
                return "redirect:" + uri;
            } else {
                model.addAttribute("message", "Login succeed!!");
                return "admin/home";
            }
        } catch (Exception e) {
            model.addAttribute("message", "Invalid username or password!!");
            return "admin/login";
        }
        
    }
    
    @GetMapping("/admin/logout")
    public String logout(Model model) {
        session.remove("user");
        model.addAttribute("message", "Logout Succeed!!");
        return "forward:/admin/login";
    }
    
    @GetMapping("/admin/home")
    public String home() {
    	return "admin/home";
    }
}
