package com.example.nnpia_semestralka.Controller;

import com.example.nnpia_semestralka.Dto.AddOrEditProductDto;
import com.example.nnpia_semestralka.Entity.Product;
import com.example.nnpia_semestralka.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @ExceptionHandler(RuntimeException.class)
    public String handleException() {
        return "error";
    }

    @GetMapping("/")
    public String showProducts(Model model) {
        model.addAttribute("productList", productRepository.findAll());
        return "product-list";
    }

    @GetMapping("/product-detail/{id}")
    public String showProductDetail(@PathVariable(required = false) Long id, Model model) {
        model.addAttribute("product", productRepository.findById(id).get());
        return "product-detail";
    }

    @GetMapping(value = {"/product-form", "/product-form/{id}"})
    public String addOrEditProduct(@PathVariable(required = false) Long id, Model model) {
        if (id != null) {
            Product product = productRepository.findById(id).orElse(new Product());
            model.addAttribute("product", product);
        } else {
            model.addAttribute("product", new AddOrEditProductDto());
        }
        return "product-form";
    }

    @PostMapping("product-form-process")
    public String addOrEditProductProcess(AddOrEditProductDto addOrEditProductDto) {
        Product product = new Product();
        product.setId(addOrEditProductDto.getId());
        product.setProductName(addOrEditProductDto.getProductName());

        productRepository.save(product);
        return "redirect:/";
    }
}
