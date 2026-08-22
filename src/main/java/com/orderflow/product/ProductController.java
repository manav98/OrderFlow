package com.orderflow.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @GetMapping
    public Product[] getProducts() {
//        Product[] productList = new Product[new Product("1", "2", 3)];
        Product[] products = new Product[3];
        products[0] = new Product("1", "2", 3.0);
        products[1] = new Product("2", "2", 3.0);
        products[2] = new Product("3", "2", 3.0);
        return products;
    }

}
