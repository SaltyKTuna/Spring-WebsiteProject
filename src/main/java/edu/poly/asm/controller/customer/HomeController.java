package edu.poly.asm.controller.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.asm.domain.Category;
import edu.poly.asm.domain.Product;
import edu.poly.asm.service.CategoryService;
import edu.poly.asm.service.ProductService;

@Controller
@RequestMapping("/site")
public class HomeController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/home")
    public String listProducts(Model model, @RequestParam(defaultValue = "1") int page,
                               @RequestParam(name = "categoryId", required = false) Integer categoryId) {
        int pageSize = 6;
        List<Category> categories = categoryService.findAll();

        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Product> productPage;
        if (categoryId != null) {
            productPage = productService.findByCategoryCategoryID(categoryId, pageable);
            model.addAttribute("selectedCategory", categoryService.getById(categoryId)); // Set selected category
        } else {
            productPage = productService.findAll(pageable);
            model.addAttribute("selectedCategory", new Category()); // Set a default empty category
        }

        model.addAttribute("categories", categories);
        model.addAttribute("products", productPage.getContent());
        model.addAttribute("pageNumber", page);
        model.addAttribute("totalPages", productPage.getTotalPages());

        return "site/home";
    }

}
