package com.application.controller;

import com.application.model.Product;
import com.application.model.ProductList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class ProductController {


    @GetMapping("/products")
    public ProductList getAll() {
        return new ProductList(Arrays.asList(
                new Product(1, "Product 1" , 3),
                new Product(2, "Product 2", 1),
                new Product(3, "Product 3", 2)
        ));
    }

    @GetMapping("products/{id}")
    public Product getOne(@PathVariable("id") int id) {
        return new Product(id, "Product ", 2);
    }
}
