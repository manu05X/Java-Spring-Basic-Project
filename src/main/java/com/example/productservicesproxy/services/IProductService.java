package com.example.productservicesproxy.services;

import com.example.productservicesproxy.models.Product;

import java.util.List;

public interface IProductService {
    Product getProductById(Long id);
    List<String> getAllProducts();
    void deleteProductById(Long id);
    void addProduct();
    void updateProduct();
}
