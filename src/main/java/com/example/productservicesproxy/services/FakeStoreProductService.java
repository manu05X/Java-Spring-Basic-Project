package com.example.productservicesproxy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Qualifier("FakeProductService")
public class FakeStoreProductService implements IProductService{
    private RestTemplateBuilder restTemplateBuilder;
    private String getProductUrl = "https://fakestoreapi.com/products/1";

    @Autowired
    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public String getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(getProductUrl, String.class);
        //return "Product fetched from FakeStore service with Id " + id;
        return "Product fetched from FakeStore service with Id " + responseEntity.toString();
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
