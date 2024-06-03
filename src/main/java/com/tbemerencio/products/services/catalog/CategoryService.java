package com.tbemerencio.products.services.catalog;

import com.tbemerencio.products.domain.category.Category;
import com.tbemerencio.products.domain.category.CategoryDTO;
import com.tbemerencio.products.domain.category.exceptions.CategoryNotFoundException;
import com.tbemerencio.products.repositories.CategoryRepository;
import com.tbemerencio.products.services.aws.AwsSnsService;
import com.tbemerencio.products.services.aws.dto.MessageDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final AwsSnsService snsService;


    public CategoryService(CategoryRepository categoryRepository, AwsSnsService snsService){
        this.categoryRepository = categoryRepository;
        this.snsService = snsService;
    }

    public Category insert(CategoryDTO categoryData){
        Category categorySaved = this.categoryRepository.insert(new Category(categoryData));
        snsService.pubish(new MessageDto(categorySaved.toString()));
        return categorySaved;
    }

    public List<Category> getAll() {
        return this.categoryRepository.findAll();
    }

    public Category update(String id, CategoryDTO categoryData) {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);

        if (!categoryData.description().isEmpty()) category.setDescription(categoryData.description());
        if (!categoryData.title().isEmpty()) category.setTitle(categoryData.title());

        Category categoryUpdated = this.categoryRepository.save(category);
        snsService.pubish(new MessageDto(categoryUpdated.toString()));
        return categoryUpdated;
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
