package com.example.productservicesproxy.services;

import com.example.productservicesproxy.models.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("SelfProductService")
public class ProductService implements IProductService{
//    @Override
//    public String getProductById(Long id) {
//        return "Product fetched from service with Id " + id;
//    }
    @Override
    public Product getProductById(Long id) {
        return null;
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
