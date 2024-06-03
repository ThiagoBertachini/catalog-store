package com.tbemerencio.products.controllers;

import com.tbemerencio.products.domain.category.Category;
import com.tbemerencio.products.domain.category.CategoryDTO;
import com.tbemerencio.products.services.catalog.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping
    private ResponseEntity<Category> insert(@RequestBody CategoryDTO categoryData){
        Category newCategory = this.categoryService.insert(categoryData);
        return ResponseEntity.ok(newCategory);
    }

    @GetMapping
    private ResponseEntity<List<Category>> getAll(){
        List<Category> categories = categoryService.getAll();
        return ResponseEntity.ok(categories);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Category> update(
            @PathVariable("id") String id, @RequestBody CategoryDTO categoryData){
        Category categories = categoryService.update(id, categoryData);
        return ResponseEntity.ok(categories);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> update(@PathVariable("id") String id){
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
