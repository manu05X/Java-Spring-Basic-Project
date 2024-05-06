package com.example.productservicesproxy.services;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{
    @Override
    public String getProductById(Long id) {
        return "Product fetched from service with Id " + id;
    }

    @Override
    public List<String> getAllProducts() {
        return null;
    }

    @Override
    public void deleteProductById(Long id) {

    }

    @Override
    public void addProduct() {

    }

    @Override
    public void updateProduct() {

    }
}
