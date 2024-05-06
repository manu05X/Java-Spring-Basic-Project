package com.example.productservicesproxy.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("FakeProductService")
public class FakeStoreProductService implements IProductService{
    @Override
    public String getProductById(Long id) {
        return "Product fetched from FakeStore service with Id " + id;
    }


    @Override
    public List<String> getAllProducts() {
        return List.of();
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
