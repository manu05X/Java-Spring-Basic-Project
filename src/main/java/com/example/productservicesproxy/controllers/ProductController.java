package com.example.productservicesproxy.controllers;

import com.example.productservicesproxy.exceptions.ProductNotFoundException;
import com.example.productservicesproxy.models.Product;
import com.example.productservicesproxy.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    //private ProductService productService;
    private IProductService productService;

    //constructor Injection of productService
    @Autowired
    public ProductController(@Qualifier("FakeProductService") IProductService productService) {
        this.productService = productService;
    }

    //Path
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        //return "Product Fetched with id:"+id;
        return productService.getProductById(id); // now the product will be fetched from service
    }

    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping()
    public Product createProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

}
