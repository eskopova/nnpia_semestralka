package com.example.nnpia_semestralka.Controller;

import com.example.nnpia_semestralka.Dto.ProductDto;
import com.example.nnpia_semestralka.Entity.Product;
import com.example.nnpia_semestralka.Service.ConversionService;
import com.example.nnpia_semestralka.Service.FileService;
import com.example.nnpia_semestralka.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.nnpia_semestralka.Service.ConversionService.toProductDto;
import static com.example.nnpia_semestralka.Service.ConversionService.toProduct;

@RestController
@CrossOrigin
public class ProductController {

    @Autowired
    private final ProductService productService;

    @Autowired
    private final FileService fileService;

    public ProductController(ProductService productService, FileService fileService) {
        this.productService = productService;
        this.fileService = fileService;
    }

    /*@ExceptionHandler(RuntimeException.class)
    public String handleException() {
        return "error";
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFound(NotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }*/

    @GetMapping("/products")
    public List<ProductDto> getAllProducts() {
        return productService.findAll()
                .stream()
                .map(ConversionService::toProductDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/products/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        Product byId = productService.findById(id);

        return toProductDto(byId);
    }

    @PostMapping("/products")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        Product product = toProduct(productDto);
        return toProductDto(productService.save(product));
    }

    @PutMapping("/products/{id}")
    public ProductDto updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        Product product = toProduct(productDto);

        return toProductDto(productService.update(id, product));
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
    }

    /*@GetMapping("/")
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
            AddOrEditProductDto addOrEditProductDto = new AddOrEditProductDto();
            addOrEditProductDto.setProductName(product.getProductName());
            addOrEditProductDto.setId(product.getId());
            addOrEditProductDto.setDescription(product.getDescription());

            model.addAttribute("product", addOrEditProductDto);
        } else {
            model.addAttribute("product", new AddOrEditProductDto());
        }
        return "product-form";
    }

    @PostMapping("/product-form-process")
    public String addOrEditProductProcess(AddOrEditProductDto addOrEditProductDto) {
        Product product = new Product();
        product.setId(addOrEditProductDto.getId());
        product.setProductName(addOrEditProductDto.getProductName());
        product.setDescription(addOrEditProductDto.getDescription());

        product.setPathToImage(fileService.upload(addOrEditProductDto.getImage()));

        productRepository.save(product);
        return "redirect:/";
    }*/
}
