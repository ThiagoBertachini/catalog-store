package com.tbemerencio.products.services.catalog;

import com.tbemerencio.products.domain.category.Category;
import com.tbemerencio.products.domain.category.CategoryDTO;
import com.tbemerencio.products.domain.category.exceptions.CategoryNotFoundException;
import com.tbemerencio.products.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public Category insert(CategoryDTO categoryData){
        return this.categoryRepository.insert(new Category(categoryData));
    }

    public List<Category> getAll() {
        return this.categoryRepository.findAll();
    }

    public Category update(String id, CategoryDTO categoryData) {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);

        if (!categoryData.description().isEmpty()) category.setDescription(categoryData.description());
        if (!categoryData.title().isEmpty()) category.setTitle(categoryData.title());

        return this.categoryRepository.save(category);
    }

    public void delete(String id) {
        this.categoryRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);
        this.categoryRepository.deleteById(id);
    }

    public Optional<Category> getById(String id) {
        return this.categoryRepository.findById(id);
    }
}
