package com.example.bookservice.service;

import com.example.bookservice.common.entity.Product;
import com.example.bookservice.exception.ResourceNotFoundException;
import com.example.bookservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public Product getProductById(int id) {
        return productRepository.findById((long) id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + id));
    }

    public Product updateProduct(Product product) {
        if (!productRepository.existsById(product.getId())) {
            throw new ResourceNotFoundException("Product not found for this id :: " + product.getId());
        }
        return productRepository.save(product);
    }

    public void deleteProduct(int id) {
        if (!productRepository.existsById((long) id)) {
            throw new ResourceNotFoundException("Product not found for this id :: " + id);
        }
        productRepository.deleteById((long) id);
    }
}
