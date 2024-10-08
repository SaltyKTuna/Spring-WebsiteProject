package edu.poly.asm.controller.admin;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.asm.domain.Account;
import edu.poly.asm.domain.Category;
import edu.poly.asm.model.AccountDto;
import edu.poly.asm.model.CategoryDto;
import edu.poly.asm.service.AccountService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("admin/accounts")
public class AccountController {
	
	@Autowired
	AccountService accountService;
	
	@GetMapping("add")
	public String add(Model model) {
		model.addAttribute("account", new AccountDto());
		return "admin/accounts/addOrEdit";
	}
	
	@RequestMapping("/accounts-manage")
	public String accountList(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
		Page<Account> resultPage = accountService.findAll(pageable);

		model.addAttribute("accountsPage", resultPage);

		int totalPages = resultPage.getTotalPages();
		if (totalPages > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.min(start + 4, totalPages); // Số lượng trang tối đa là 5

			if (end == totalPages) {
				start = Math.max(1, end - 4); // Giữ cho có đủ 5 trang
			}

			List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		
		return "admin/accounts/accounts-manage";
	}
	
	@GetMapping("/edit/{username}")
	public ModelAndView edit(ModelMap model, @PathVariable("username") String username) {
		Optional<Account> opt = accountService.findById(username);
		AccountDto dto = new AccountDto();
		
		if(opt.isPresent()) {
			Account entity = opt.get();
			BeanUtils.copyProperties(entity, dto);
			
			model.addAttribute("account", dto);
			return new ModelAndView("admin/accounts/addOrEdit");
		}
		model.addAttribute("message", "Account not found!");
		return new ModelAndView("forward:/admin/accounts/list", model);
	}
	
	@PostMapping("/saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("account") AccountDto dto,
			BindingResult result) {
		if(result.hasErrors()) {
			return new ModelAndView("admin/accounts/addOrEdit");
		}
		Account entity = new Account();
		BeanUtils.copyProperties(dto, entity);
		accountService.save(entity);
		
		model.addAttribute("message", "Account is saved!!");
		return new ModelAndView("forward:/admin/accounts/accounts-manage", model);
	}
	
	@GetMapping("/delete/{username}")
	public ModelAndView delete(ModelMap model, @PathVariable("username") String username) {
		accountService.deleteById(username);
		
		model.addAttribute("message", "Delete completed!!");
		return new ModelAndView("forward:/admin/accounts/list", model);
	}
	
}
