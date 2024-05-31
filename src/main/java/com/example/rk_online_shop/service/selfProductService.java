package com.example.rk_online_shop.service;

import com.example.rk_online_shop.exceptions.ProductNotFoundException;
import com.example.rk_online_shop.model.Category;
import com.example.rk_online_shop.model.Product;
import com.example.rk_online_shop.repositories.CategoryRepository;
import com.example.rk_online_shop.repositories.ProductRepository;
import com.example.rk_online_shop.repositories.projections.CategoryProjection;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class selfProductService implements ProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public selfProductService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
       Optional<Product> p = productRepository.findById(productId);
        if(p.isPresent()){
            return p.get();
        }
        throw new ProductNotFoundException("product not found");
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        Category cat = categoryRepository.findByTitle(product.getCategory().getTitle());
        if(cat == null){
            Category newCat = new Category();
            newCat.setTitle(product.getCategory().getTitle());
            Category newRow = categoryRepository.save(newCat);
            product.setCategory(newRow);
        }
        else{
            product.setCategory(cat);
        }
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long productId, Product product) throws ProductNotFoundException {
        Optional<Product> p = productRepository.findById(productId);
        if(p.isPresent()){
            Category cat = categoryRepository.findByTitle(product.getCategory().getTitle());
            if(cat == null){
                Category newCat = new Category();
                newCat.setTitle(product.getCategory().getTitle());
                Category newRow = categoryRepository.save(newCat);
                product.setCategory(newRow);
            }
            else{
                product.setCategory(cat);
            }
            product.setId(productId);
            return productRepository.save(product);
        }
        throw new ProductNotFoundException("product not found, Id : " + productId);
    }

    @Override
    @Transactional
    public Product deleteProduct(Long productId) throws ProductNotFoundException {
        Optional<Product> p = productRepository.findById(productId);
        if(p.isPresent()){
            productRepository.deleteProductById(productId);
            return p.get();
        }
        throw new ProductNotFoundException("product not found");
    }

    @Override
    public List<Product> getProductByCategory(String category) {
        return productRepository.getAllProductBycategory(category);
    }

    @Override
    public List<CategoryProjection> getAllCategory() {
        return categoryRepository.getAllCategory();
    }
}
