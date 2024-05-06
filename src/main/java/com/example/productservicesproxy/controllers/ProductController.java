package com.example.productservicesproxy.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    //Path
    @GetMapping("/{id}")
    public String getProductById(@PathVariable("id") Long id) {
        return "Product Fetched with id:"+id;
    }
    @GetMapping()
    public List<String> getAllProducts() {
        return Collections.emptyList();
    }
}
