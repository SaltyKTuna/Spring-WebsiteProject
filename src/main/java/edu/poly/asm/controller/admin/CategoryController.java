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
import org.springframework.data.domain.Sort;
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

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import edu.poly.asm.domain.Category;
import edu.poly.asm.model.CategoryDto;
import edu.poly.asm.service.CategoryService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("admin/categories")
public class CategoryController {
	@Autowired
	CategoryService categoryService;

	@GetMapping("add")
	public String add(Model model) {
		model.addAttribute("category", new CategoryDto());
		return "admin/categories/addOrEdit";
	}

	@GetMapping("edit/{categoryID}")
	public ModelAndView edit(ModelMap model, @PathVariable("categoryID") Integer categoryID) {
		Optional<Category> opt = categoryService.findById(categoryID);
		CategoryDto dto = new CategoryDto();
		if (opt.isPresent()) {
			Category entity = opt.get();
			BeanUtils.copyProperties(entity, dto);
			dto.setIsEdit(true);

			model.addAttribute("category", dto);
			return new ModelAndView("admin/categories/addOrEdit");
		}
		model.addAttribute("message", "Category not found!");
		return new ModelAndView("forward:/admin/categories/list", model);
	}

	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("category") CategoryDto dto,
			BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("admin/categories/addOrEdit");
		}
		Category entity = new Category();
		BeanUtils.copyProperties(dto, entity);
		dto.setIsEdit(false);
		categoryService.save(entity);

		model.addAttribute("message", "Category is saved!!");
		return new ModelAndView("forward:/admin/categories/list", model);
	}

	@RequestMapping("/list")
	public String listCategories(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
		Page<Category> resultPage = categoryService.findAll(pageable);

		model.addAttribute("categoryPage", resultPage);

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

		return "admin/categories/list";
	}

	@GetMapping("/search")
	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
		Page<Category> resultPage = null;

		if (StringUtils.hasText(name)) {
			resultPage = categoryService.findByNameContaining(name, pageable);
			model.addAttribute("name", name);
			if (resultPage.isEmpty()) {
				model.addAttribute("message", "Category not found!");
			}
		} else {
			resultPage = categoryService.findAll(pageable);
			if (resultPage.isEmpty()) {
				model.addAttribute("message", "Category not found!");
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

		model.addAttribute("categoryPage", resultPage);
		return "admin/categories/list";
	}

	@GetMapping("delete/{categoryID}")
	public ModelAndView delete(ModelMap model, @PathVariable("categoryID") Integer categoryID) {
		categoryService.deleteById(categoryID);

		model.addAttribute("message", "Category Deleted!!");

		return new ModelAndView("forward:/admin/categories/list", model);
	}
}
