package com.springeshop.controllers;

import com.springeshop.data.domain.Bucket;
import com.springeshop.data.domain.PurchasedItem;
import com.springeshop.data.domain.User;
import com.springeshop.data.dto.ProductDTO;
import com.springeshop.repositories.UserRepository;
import com.springeshop.service.BucketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/bucket")
@RequiredArgsConstructor
public class BucketController {

    private final BucketService bucketService;
    @PostMapping("/{productId}")
    public String addItemToBucket(@PathVariable Long productId, Principal principal){
        String username = principal.getName();
        bucketService.addItemToBucket(productId, username);
        return "product-list";
    }

    @DeleteMapping("/{itemId}")
    public String removeItemFromBucket(@PathVariable Long itemId){
        bucketService.removeItemFromBucket(itemId);
        return "bucket-list";
    }

    @GetMapping
    public String getBucketItems(Model model, Principal principal){
        if(principal == null){
            return "login";
        }
        List<ProductDTO> BucketProductDTOs = bucketService.getAll(principal.getName());
        model.addAttribute("bucket", BucketProductDTOs);
        return "bucket-list";
    }
}
