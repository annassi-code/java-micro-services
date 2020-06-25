package com.application.service;

import com.application.model.Category;
import com.application.model.Product;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CategoryService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getCategoryFallback")
    public Category getCategory(Product product) {
        return restTemplate.getForObject("http://category-service/categories/" + product.getCategoryId(), Category.class);
    }

    private Category getCategoryFallback(Product p) {
        return new Category(-1, "Categorie error");
    }
}

