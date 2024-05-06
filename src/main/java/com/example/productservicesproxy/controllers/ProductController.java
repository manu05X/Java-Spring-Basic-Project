package com.example.productservicesproxy.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class ProductController {
    //Path
    @GetMapping("/product/{id}")
    public String getProductById(@PathVariable("id") Long id) {
        return "Product Fetched with id:"+id;
    }
}
