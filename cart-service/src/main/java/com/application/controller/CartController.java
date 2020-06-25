package com.application.controller;

import com.application.model.Category;
import com.application.service.CartService;
import com.application.model.Product;
import com.application.model.ProductItem;
import com.application.service.CategoryService;
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
    CategoryService categoryService;

    @GetMapping("/carts")
    public List<ProductItem> getAll() {

        List<ProductItem> productItems = new ArrayList<>();
        cartService.getProductList().getProducts().stream().forEach( product ->  {
            Category category = categoryService.getCategory(product);

            ProductItem productItem = new ProductItem();
            productItem.setCategoryName(category.getName());
            productItem.setId(product.getId());
            productItem.setProductName(product.getName());
            productItems.add(productItem);
        });
        return productItems;
    }
}
