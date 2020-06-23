package com.demomicro.cartservice.service;

import com.demomicro.cartservice.model.Product;
import com.demomicro.cartservice.model.ProductList;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class CartService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod="getProductError")
    public ProductList getProductList() {
        return restTemplate.getForObject("http://products-service/products", ProductList.class);
    }
    public ProductList getProductError() {
        return new ProductList(Arrays.asList(new Product(-1, "error", 1)));
    }
}
