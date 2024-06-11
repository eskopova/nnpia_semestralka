package com.example.nnpia_semestralka.Controller;

import com.example.nnpia_semestralka.Dto.ProductDto;
import com.example.nnpia_semestralka.Entity.ProductCategory;
import com.example.nnpia_semestralka.Entity.Shop;
import com.example.nnpia_semestralka.Repository.ShopRepository;
import com.example.nnpia_semestralka.Security.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.swing.text.html.Option;
import java.util.Optional;


@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtUtil jwtUtil;

    @Autowired
    private ShopRepository shopRepository;

    @Test
    @WithMockUser(username="admin", authorities={"ADMIN"})
    void createNewProduct_Success() throws Exception {
        Shop shop = new Shop();
        shop.setShopName("test");
        shopRepository.save(shop);

        ProductDto productDto = new ProductDto();
        productDto.setProductName("test");
        productDto.setShopName("test");
        productDto.setCategory(ProductCategory.Obyčejné.name());
        productDto.setDescription("Test");
        productDto.setPathToImage("test.jpg");
        String s = objectMapper.writeValueAsString(productDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                .content(s)
                .contentType(MediaType.APPLICATION_JSON))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productName").value("test"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.shopName").value("test"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category").value(ProductCategory.Obyčejné.name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Test"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pathToImage").value("test.jpg"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }
}
