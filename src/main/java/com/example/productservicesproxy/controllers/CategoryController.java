package com.example.productservicesproxy.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    @GetMapping("/categories")
    public String getAllCategories() {
        return "Getting all categories";
    }

    public String getProductsInCategory() {
        return "Getting products in category ";
    }
}

/*
* @RestController -> this tells spring that this has some rest apis implemented or some endpoint in it. This annotation will register this class with
* spring dispatcher. If you get some request you can call this controller.
*
* */
