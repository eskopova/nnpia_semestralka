package com.example.nnpia_semestralka;

import com.example.nnpia_semestralka.Entity.Product;
import com.example.nnpia_semestralka.Repository.ProductRepository;
import com.example.nnpia_semestralka.Service.ShoppingCartService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
public class ShoppingCartTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Test
    void shoppingCartTest() {
        Product product = new Product();
        product.setProductName("Maki");
        productRepository.save(product);
        List<Product> all = productRepository.findAll();

        Long productId = all.get(0).getId();

        shoppingCartService.add(productId);

        assert(shoppingCartService.getCart().size() == 1);
        assert(shoppingCartService.getCart().containsKey(all.get(0)));
        assert(shoppingCartService.getCart().get(all.get(0)) == 1);

        shoppingCartService.add(productId);

        assert(shoppingCartService.getCart().size() == 2);

        shoppingCartService.remove(productId);

        assert(shoppingCartService.getCart().get(all.get(0)) == 1);

        shoppingCartService.remove(productId);

        assert(shoppingCartService.getCart().get(all.get(0)) == 0);
    }
}
