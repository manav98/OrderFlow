package com.orderflow.product;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
//        List<Product> productList = new ArrayList<>();
//        productList.add(new Product(UUID.randomUUID(), "Cello Bottle Black", "Cello black bottle 1 L", new BigDecimal("100")));
//        productList.add(new Product(UUID.randomUUID(), "Bat", "BAS Bat Pro", new BigDecimal("450")));
//        productList.add(new Product(UUID.randomUUID(), "Bat Mobile", "Black Ferrari converted bat mobile", new BigDecimal("7000000")));
//        return productList;
        return productRepository.findAll();
    }

    public Product saveProduct(String sku, String name, BigDecimal cost) {
        Product product = new Product(UUID.randomUUID(), sku, name, cost);
        productRepository.save(product);
        return product;
    }


}
