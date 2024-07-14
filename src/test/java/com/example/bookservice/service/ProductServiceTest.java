package com.example.bookservice.service;

import com.example.bookservice.common.entities.Product;
import com.example.bookservice.exception.ResourceNotFoundException;
import com.example.bookservice.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduct() {
        Product product = new Product();
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product createdProduct = productService.createProduct(product);

        assertNotNull(createdProduct);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testGetAllProducts() {
        Product product1 = new Product();
        Product product2 = new Product();
        List<Product> productList = Arrays.asList(product1, product2);
        when(productRepository.findAll()).thenReturn(productList);

        List<Product> products = productService.getAllProducts();

        assertEquals(2, products.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testGetProductById() {
        Product product = new Product();
        product.setId(1L);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product foundProduct = productService.getProductById(1);

        assertNotNull(foundProduct);
        assertEquals(1L, foundProduct.getId());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void testGetProductById_NotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productService.getProductById(1));
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateProduct() {
        Product product = new Product();
        product.setId(1L);
        when(productRepository.existsById(1L)).thenReturn(true);
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product updatedProduct = productService.updateProduct(product);

        assertNotNull(updatedProduct);
        assertEquals(1L, updatedProduct.getId());
        verify(productRepository, times(1)).existsById(1L);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testUpdateProduct_NotFound() {
        Product product = new Product();
        product.setId(1L);
        when(productRepository.existsById(1L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> productService.updateProduct(product));
        verify(productRepository, times(1)).existsById(1L);
    }

    @Test
    void testDeleteProduct() {
        when(productRepository.existsById(1L)).thenReturn(true);
        doNothing().when(productRepository).deleteById(1L);

        productService.deleteProduct(1);

        verify(productRepository, times(1)).existsById(1L);
        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteProduct_NotFound() {
        when(productRepository.existsById(1L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> productService.deleteProduct(1));
        verify(productRepository, times(1)).existsById(1L);
    }
}
