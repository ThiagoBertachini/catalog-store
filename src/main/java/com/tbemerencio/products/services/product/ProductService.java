package com.tbemerencio.products.services.product;

import com.tbemerencio.products.domain.category.Category;
import com.tbemerencio.products.domain.category.exceptions.CategoryNotFoundException;
import com.tbemerencio.products.domain.product.Product;
import com.tbemerencio.products.domain.product.ProductDTO;
import com.tbemerencio.products.domain.product.exceptions.ProductNotFoundException;
import com.tbemerencio.products.repositories.ProductRepository;
import com.tbemerencio.products.services.aws.AwsSnsService;
import com.tbemerencio.products.services.aws.dto.MessageDto;
import com.tbemerencio.products.services.catalog.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductService {

    private final CategoryService categoryService;
    private final AwsSnsService snsService;
    private final ProductRepository productRepository;

    public ProductService(CategoryService categoryService,
                          ProductRepository productRepository,
                          AwsSnsService awsSnsService){
        this.categoryService = categoryService;
        this.productRepository = productRepository;
        this.snsService = awsSnsService;
    }

    public Product insert(ProductDTO productData){
        categoryService.getById(productData.categoryId())
                .orElseThrow(CategoryNotFoundException::new);
        Product product = new Product(productData);

        snsService.pubish(new MessageDto(product.toString()));

        return this.productRepository.insert(product);
    }

    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    public Product update(String id, ProductDTO productData) {

        Product product = this.productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        if (Objects.nonNull(productData.categoryId())) {
            this.categoryService.getById(productData.categoryId()).orElseThrow(
                    CategoryNotFoundException::new);
            product.setCategory(productData.categoryId());
        }

        if (!productData.description().isEmpty()) product.setDescription(productData.description());
        if (!productData.title().isEmpty()) product.setTitle(productData.title());
        if (Objects.nonNull(productData.price())) product.setPrice(productData.price());

        snsService.pubish(new MessageDto(product.toString()));
        return this.productRepository.save(product);
    }

    public void delete(String id) {
        this.productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
        this.productRepository.deleteById(id);
    }
}
