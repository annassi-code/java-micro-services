package com.application.controller;

import com.application.model.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class CategoryController {

    @GetMapping("categories")
    public List<Category> getAll() {
        return Arrays.asList(
                new Category(1, "Categorie 1"),
                new Category(2, "Categorie 2"),
                new Category(3, "Categorie 3")
        );
    }

    @GetMapping("categories/{id}")
    public Category getAll(@PathVariable("id") int id) {

        return new Category(id, "Category "+id);
    }
}
