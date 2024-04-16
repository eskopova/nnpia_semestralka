package com.example.nnpia_semestralka.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

    @GetMapping("/checkout")
    public String checkout() {
        return "checkout";
    }
}
