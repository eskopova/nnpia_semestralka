package com.example.nnpia_semestralka.Controller;

import com.example.nnpia_semestralka.Entity.ProductCategory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class ProductCategoryController {

    @GetMapping("/product-categories")
    public List<String> getAllProductCategories() {
        return Arrays.stream(ProductCategory.values())
                .map(ProductCategory::name)
                .collect(Collectors.toList());
    }

}
