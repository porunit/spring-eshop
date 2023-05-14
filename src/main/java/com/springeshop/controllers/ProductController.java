package com.springeshop.controllers;

import com.springeshop.data.dto.ProductDTO;
import com.springeshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public String productList(Model model) {
        List<ProductDTO> productDTOs = productService.getAll();
        model.addAttribute("products", productDTOs);
        return "product-list";
    }

    @GetMapping("/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new ProductDTO());
        return "product";
    }

    @PostMapping("/new")
    public String saveProduct(ProductDTO productDTO, Model model) {
        productService.save(productDTO);
        return "redirect:/products";
    }
}
