package edu.poly.asm.controller.customer;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.asm.domain.Customer;
import edu.poly.asm.model.CustomerDto;
import edu.poly.asm.service.CustomerService;
import edu.poly.asm.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

@Controller
@RequestMapping("site")
public class RegisterController {
    @Autowired 
    CustomerService customerService;

    @Autowired
    MailService mailer;

    private Customer registerCustomer;
    private String vertificateString;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("customer", new CustomerDto());
        return "site/register";
    }

    @PostMapping("/saveOrUpdate")
    public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("customer") CustomerDto dto,
                                     BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return new ModelAndView("site/register");
        }
        dto.setStatus((short) 1);
        dto.setIsEdit(false);
        Customer entity = new Customer();
        BeanUtils.copyProperties(dto, entity);

        registerCustomer = new Customer();
        BeanUtils.copyProperties(dto, registerCustomer);

        return new ModelAndView("forward:/site/vertificateForm", model);
    }

    @RequestMapping("/vertificateForm")
    public String showVertificateForm() {
        try {
            mailer.push(registerCustomer.getEmail(), "Account Vertification", "vertificate");
            mailer.testMailSender();
            mailer.run();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        vertificateString = "vertificate";
        return "site/emailVertificate";
    }

    @PostMapping("/emailVertificate")
    public String emailVertificate(Model model, @RequestParam("verification") String verification) {
        if (vertificateString.equals(verification)) {
            registerCustomer.setStatus((short) 2);
            customerService.save(registerCustomer); // Update the existing customer
            model.addAttribute("message", "Verification succeeded!!");
        } else {
            model.addAttribute("message", "Verification failed!!");
        }

        return "site/login";
    }
}
