package com.demomicro.cartservice.controller;

import com.demomicro.cartservice.model.Category;
import com.demomicro.cartservice.model.Product;
import com.demomicro.cartservice.model.ProductItem;
import com.demomicro.cartservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@EnableCircuitBreaker
@EnableHystrixDashboard
public class CartController {

    @Autowired
    CartService cartService;
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/carts")
    public List<ProductItem> getAll() {

        List<ProductItem> productItems = new ArrayList<>();
        for (Product product : cartService.getProductList().getProducts()) {
            Category category = getCategory(product);
            ProductItem productItem = new ProductItem();
            productItem.setCategoryName(category.getName());
            productItem.setId(product.getId());
            productItem.setProductName(product.getName());
            productItems.add(productItem);
        }
        return productItems;

    }

    public Category getCategory(Product product) {
        return restTemplate.getForObject("http://categories-service/categories/" + product.getCategoryId(), Category.class);
    }

    public Category getCategoryError(Product product) {

        return new Category(-1, "error");

    }

}
