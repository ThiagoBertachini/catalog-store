package com.tbemerencio.products.controllers;

import com.tbemerencio.products.domain.product.Product;
import com.tbemerencio.products.domain.product.ProductDTO;
import com.tbemerencio.products.services.product.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping
    private ResponseEntity<Product> insert(@RequestBody ProductDTO productData){
        Product newProduct = this.productService.insert(productData);
        return ResponseEntity.ok(newProduct);
    }

    @GetMapping
    private ResponseEntity<List<Product>> getAll(){
        List<Product> products = productService.getAll();
        return ResponseEntity.ok(products);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Product> update(
            @PathVariable("id") String id, @RequestBody ProductDTO productData){
        Product product = productService.update(id, productData);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> update(@PathVariable("id") String id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
