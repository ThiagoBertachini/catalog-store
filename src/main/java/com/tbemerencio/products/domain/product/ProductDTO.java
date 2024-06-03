package com.tbemerencio.products.domain.product;

import com.tbemerencio.products.domain.category.Category;
import com.tbemerencio.products.domain.category.CategoryDTO;

public record ProductDTO(String title,
                         String description,
                         String ownerId,
                         Integer price,
                         String categoryId){
}
