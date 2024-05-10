package com.example.productservicesproxy.services;

import com.example.productservicesproxy.dtos.FakeStoreProductDto;
import com.example.productservicesproxy.exceptions.ProductNotFoundException;
import com.example.productservicesproxy.models.Categories;
import com.example.productservicesproxy.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("FakeProductService")
public class FakeStoreProductService implements IProductService{
    private RestTemplateBuilder restTemplateBuilder;
    //private String getProductUrl = "https://fakestoreapi.com/products/1";
    private String specificProductUrl = "https://fakestoreapi.com/products/{id}";
    private String genericProductUrl = "https://fakestoreapi.com/products";

    @Autowired
    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity(specificProductUrl, FakeStoreProductDto.class, id);
        if(responseEntity.getBody() == null){
            throw new ProductNotFoundException("Product Not Found with id: " + id);
        }
        return getProductFromFakeStoreProductDto(responseEntity.getBody());
    }


    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> responseEntity = restTemplate.getForEntity(genericProductUrl, FakeStoreProductDto[].class);
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : responseEntity.getBody()) {
            products.add(getProductFromFakeStoreProductDto(fakeStoreProductDto));
        }
        return products;
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

    private Product getProductFromFakeStoreProductDto(FakeStoreProductDto fakeStoreProductDto){
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());

        Categories categories = new Categories();
        categories.setNameCategory(fakeStoreProductDto.getCategory());
        product.setCategories(categories);

        return product;
    }
}
