package com.example.productservicesproxy.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Product extends BaseModel{
    private String title;
    private double price;
    private String description;
    private Categories categories;

    private List<String> allowedUser;
}