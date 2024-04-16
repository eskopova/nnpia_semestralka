package com.example.nnpia_semestralka.Controller;

import com.example.nnpia_semestralka.Service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/shopping-cart-add/{id}")
    public String shoppingCartAdd(@PathVariable Long id, Model model) {
        shoppingCartService.add(id);
        return "redirect:/shopping-cart";
    }

    @GetMapping("/shopping-cart-remove/{id}")
    public String shoppingCartRemove(@PathVariable Long id, Model model) {
        shoppingCartService.remove(id);
        return "redirect:/shopping-cart";
    }

    @GetMapping("/shopping-cart")
    public String showShoppingCart(Model model) {
        model.addAttribute("shoppingCart", shoppingCartService.getCart());
        return "shopping-cart";
    }
}
