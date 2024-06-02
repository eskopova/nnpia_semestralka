package com.example.nnpia_semestralka.Controller;

import com.example.nnpia_semestralka.Dto.ProductDto;
import com.example.nnpia_semestralka.Entity.Product;
import com.example.nnpia_semestralka.Service.ConversionService;
import com.example.nnpia_semestralka.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private final ProductService productService;

    @Autowired
    private final ConversionService conversionService;

    public ProductController(ProductService productService, ConversionService conversionService) {
        this.productService = productService;
        this.conversionService = conversionService;
    }

    /*@ExceptionHandler(RuntimeException.class)
    public String handleException() {
        return "error";
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFound(NotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }*/

    @GetMapping("")
    public List<ProductDto> getAllProducts(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "4") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        return productService.findAll(pageNumber, pageSize, sortBy)
                .stream()
                .map(conversionService::toProductDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        Product byId = productService.findById(id);

        return conversionService.toProductDto(byId);
    }

    @GetMapping("/by-category/{name}")
    public List<ProductDto> getProductsByCategory(
            @PathVariable String name,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "4") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        return productService.findByCategory(name, pageNumber, pageSize, sortBy)
                .stream()
                .map(conversionService::toProductDto)
                .collect(Collectors.toList());
    }

    @PostMapping("")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        Product product = conversionService.toProduct(productDto);
        return conversionService.toProductDto(productService.save(product));
    }

    @PutMapping("/{id}")
    public ProductDto updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        Product product = conversionService.toProduct(productDto);

        return conversionService.toProductDto(productService.update(id, product));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
