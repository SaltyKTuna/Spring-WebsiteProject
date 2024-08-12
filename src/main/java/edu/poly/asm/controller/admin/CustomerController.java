package edu.poly.asm.controller.admin;

import java.util.Date;
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
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.asm.domain.Category;
import edu.poly.asm.domain.Customer;
import edu.poly.asm.model.CategoryDto;
import edu.poly.asm.model.CustomerDto;
import edu.poly.asm.service.CustomerService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("admin/customers")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("add")
	public String add(Model model) {
		model.addAttribute("customer", new CustomerDto());
		return "admin/customers/addOrEdit";
	}

	@GetMapping("edit/{customerID}")
	public ModelAndView edit(ModelMap model, @PathVariable("customerID") Integer customerID) {
		Optional<Customer> opt = customerService.findById(customerID);
		CustomerDto dto = new CustomerDto();
		if (opt.isPresent()) {
			Customer entity = opt.get();
			BeanUtils.copyProperties(entity, dto);
			dto.setIsEdit(true);

			model.addAttribute("customer", dto);
			return new ModelAndView("admin/customers/addOrEdit");
		}
		model.addAttribute("message", "Customer not found!");
		return new ModelAndView("forward:/admin/customers/list", model);
	}
	
	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("customer") CustomerDto dto,
			BindingResult result) {
		if (result.hasErrors()) {
	        model.addAttribute("errors", result.getAllErrors());
			return new ModelAndView("admin/customers/addOrEdit");
		}
		dto.setStatus((short) 1);
		dto.setIsEdit(false);
		Customer entity = new Customer();
		BeanUtils.copyProperties(dto, entity);

		
		customerService.save(entity);

		model.addAttribute("message", "Customer is saved!!");
		return new ModelAndView("forward:/admin/customers/list", model);
	}

	@RequestMapping("/list")
	public String listCategories(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
		Page<Customer> resultPage = customerService.findAll(pageable);

		model.addAttribute("customerPage", resultPage);

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

		return "admin/customers/list";
	}

	@GetMapping("/search")
	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
		Page<Customer> resultPage = null;

		if (StringUtils.hasText(name)) {
			resultPage = customerService.findByNameContaining(name, pageable);
			model.addAttribute("name", name);
			if (resultPage.isEmpty()) {
				model.addAttribute("message", "Customer not found!");
			}
		} else {
			resultPage = customerService.findAll(pageable);
			if (resultPage.isEmpty()) {
				model.addAttribute("message", "Customer not found!");
			}
		}

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

		model.addAttribute("customerPage", resultPage);
		return "admin/customers/list";
	}

	@GetMapping("delete/{customerID}")
	public ModelAndView delete(ModelMap model, @PathVariable("customerID") Integer customerID) {
		customerService.deleteById(customerID);

		model.addAttribute("message", "Customer Deleted!!");

		return new ModelAndView("forward:/admin/customers/list", model);
	}
}
