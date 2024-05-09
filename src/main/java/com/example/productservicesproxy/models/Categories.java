package com.example.productservicesproxy.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Categories extends BaseModel{
    private String nameCategory;
    private String descriptionCategory;
    private List<Product> productList;
}