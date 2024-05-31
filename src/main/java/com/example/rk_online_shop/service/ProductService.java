package com.example.rk_online_shop.service;

import com.example.rk_online_shop.exceptions.ProductNotFoundException;
import com.example.rk_online_shop.model.Product;
import com.example.rk_online_shop.repositories.projections.CategoryProjection;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long productId) throws ProductNotFoundException;
    List<Product> getAllProducts();
    Product createProduct(Product product);
    Product updateProduct(Long productId, Product product) throws ProductNotFoundException;
    Product deleteProduct(Long productId) throws  ProductNotFoundException;
    List<Product> getProductByCategory(String category);
    List<CategoryProjection> getAllCategory();
}