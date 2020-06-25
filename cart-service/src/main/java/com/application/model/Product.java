package com.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Product {
    private int id;
    private String name;
    private int categoryId;

}
