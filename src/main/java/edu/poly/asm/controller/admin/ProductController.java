package edu.poly.asm.controller.admin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.asm.domain.Category;
import edu.poly.asm.domain.Customer;
import edu.poly.asm.domain.Product;
import edu.poly.asm.model.CategoryDto;
import edu.poly.asm.model.CustomerDto;
import edu.poly.asm.model.ProductDto;
import edu.poly.asm.service.CategoryService;
import edu.poly.asm.service.ProductService;
import jakarta.validation.Valid;
@Controller
@RequestMapping("/admin/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    // GET request to display form for adding new product
    @GetMapping("/add")
    public String add(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("product", new ProductDto());
        return "admin/products/productmanage";
    }

    // POST request to handle save or update product
    @PostMapping("/saveOrUpdate")
    public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("product") ProductDto dto,
                                     BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return new ModelAndView("admin/products/productmanage");
        }

        MultipartFile imageFile = dto.getImageFile();

        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                String uploadDir = "src/main/resources/static/images/";
                String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
                Path uploadPath = Paths.get(uploadDir);

                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                Path filePath = uploadPath.resolve(fileName);
                Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                dto.setImage("/images/" + fileName);

            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("message", "Failed to upload file: " + e.getMessage());
                return new ModelAndView("admin/products/productmanage", model);
            }
        }

        dto.setStatus((short) 1);
        dto.setIsEdit(false);
        System.out.println(dto.getCategoryID());
        Product entity = new Product();
        BeanUtils.copyProperties(dto, entity);
        Category category = categoryService.findByCategoryID(dto.getCategoryID());
        entity.setCategory(category);
        
        productService.save(entity);

        model.addAttribute("message", "Sản phẩm đã được lưu!!");
        return new ModelAndView("redirect:/admin/products/list", model);
    }

    // GET request to display form for editing a product
    @GetMapping("/edit/{productID}")
    public String edit(Model model, @PathVariable("productID") Integer productID) {
        Optional<Product> opt = productService.findById(productID);
        if (opt.isPresent()) {
            ProductDto dto = new ProductDto();
            BeanUtils.copyProperties(opt.get(), dto);
            dto.setIsEdit(true);
            
            List<Category> categories = categoryService.findAll();
            model.addAttribute("categories", categories);
            model.addAttribute("product", dto);
            return "admin/products/productmanage";
        }
        model.addAttribute("message", "Product not found!");
        return "forward:/admin/products/list";
    }


    // GET request to display list of products
    @GetMapping("/list")
    public String list(Model model, @RequestParam("page") Optional<Integer> page,
                       @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
        Page<Product> resultPage = productService.findAll(pageable);

        model.addAttribute("productPage", resultPage);

        int totalPages = resultPage.getTotalPages();
        if (totalPages > 0) {
            int start = Math.max(1, currentPage - 2);
            int end = Math.min(start + 4, totalPages);

            if (end == totalPages) {
                start = Math.max(1, end - 4);
            }

            List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "admin/products/list";
    }

    // GET request to search products by name
    @GetMapping("/search")
    public String search(ModelMap model, @RequestParam(name = "name", required = false) String name,
                         @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
        Page<Product> resultPage;

        if (StringUtils.hasText(name)) {
            resultPage = productService.findByNameContaining(name, pageable);
            model.addAttribute("name", name);
            if (resultPage.isEmpty()) {
                model.addAttribute("message", "Product not found!");
            }
        } else {
            resultPage = productService.findAll(pageable);
            if (resultPage.isEmpty()) {
                model.addAttribute("message", "Product not found!");
            }
        }

        int totalPages = resultPage.getTotalPages();
        if (totalPages > 0) {
            int start = Math.max(1, currentPage - 2);
            int end = Math.min(start + 4, totalPages);

            if (end == totalPages) {
                start = Math.max(1, end - 4);
            }

            List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("productPage", resultPage);
        return "admin/products/list";
    }

    // GET request to delete a product
    @GetMapping("/delete/{productID}")
    public ModelAndView delete(ModelMap model, @PathVariable("productID") Integer productID) {
        productService.deleteById(productID);
        model.addAttribute("message", "Product Deleted!!");
        return new ModelAndView("redirect:/admin/products/list", model);
    }
}
