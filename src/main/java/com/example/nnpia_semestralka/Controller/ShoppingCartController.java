package com.example.nnpia_semestralka.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ShoppingCartController {

    @GetMapping("/shopping-cart-add/{id}")
    public String shoppingCartAdd(@PathVariable Long id, Model model) {

        return "redirect:/shopping-cart-add";
    }

    @GetMapping("/shopping-cart-remove/{id}")
    public String shoppingCartRemove(@PathVariable Long id, Model model) {

        return "redirect:/shopping-cart-add";
    }

    @GetMapping("/shopping-cart")
    public String showShoppingCart() {

    }
}
