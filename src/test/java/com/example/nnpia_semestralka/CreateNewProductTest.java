package com.example.nnpia_semestralka;

import com.example.nnpia_semestralka.Dto.ProductDto;
import com.example.nnpia_semestralka.Entity.ProductCategory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class CreateNewProductTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createNewProductSuccess() throws Exception {

        ProductDto productDto = new ProductDto();
        productDto.setProductName("createProductTest1");
        productDto.setPathToImage("test.jpg");
        productDto.setDescription("popis");
        productDto.setShopName("Tesco");
        String s = objectMapper.writeValueAsString(productDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                .content(s)
                .contentType(MediaType.APPLICATION_JSON))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productName", Is.is(productDto.getProductName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pathToImage", Is.is(productDto.getPathToImage())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", Is.is(productDto.getDescription())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category", Is.is(productDto.getCategory())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.shopName", Is.is(productDto.getShopName())));

    }

    @Test
    public void createNewProductInvalidPathToImage() throws Exception {

        ProductDto productDto = new ProductDto();
        productDto.setProductName("createProductTest2");
        productDto.setDescription("popis");
        productDto.setPathToImage(null);
        productDto.setShopName("Tesco");
        productDto.setCategory(ProductCategory.Obyčejné.name());
        String s = objectMapper.writeValueAsString(productDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                .content(s)
                .contentType(MediaType.APPLICATION_JSON))

                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }
}
