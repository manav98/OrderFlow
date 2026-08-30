package com.orderflow.product;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(UUID id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product Not Found"));
    }

    public Product createProduct(String sku, String name, BigDecimal price) {
        Product product = new Product(UUID.randomUUID(), sku, name, price);
        return productRepository.save(product);
    }

}
