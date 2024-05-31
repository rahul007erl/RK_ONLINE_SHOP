package com.example.rk_online_shop.controller;

import com.example.rk_online_shop.dto.ErrorDto;
import com.example.rk_online_shop.exceptions.ProductNotFoundException;
import com.example.rk_online_shop.model.Product;
import com.example.rk_online_shop.repositories.projections.CategoryProjection;
import com.example.rk_online_shop.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;

@RestController
public class ProductController {
     private final ProductService productService;
     public ProductController(@Qualifier("selfProductService") ProductService productService){
         this.productService = productService;
     }
    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product){
         return productService.createProduct(product);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long productId) throws ProductNotFoundException {
        Product currentProduct = productService.getSingleProduct(productId);
        ResponseEntity<Product> res = new ResponseEntity<>(currentProduct, HttpStatus.OK);
        return res;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
         return productService.getAllProducts();
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable("id") Long productId, @RequestBody Product product) throws ProductNotFoundException{
        return productService.updateProduct(productId, product);
    }

    @DeleteMapping("/products/{id}")
    public Product deleteProduct(@PathVariable("id") Long productId) throws ProductNotFoundException{
         return productService.deleteProduct(productId);
    }

    @GetMapping("/products/category/{category}")
    public List<Product> getProductByCategory(@PathVariable("category") String category){
         return productService.getProductByCategory(category);
    }

    @GetMapping("/products/categories")
    public List<CategoryProjection> getAllCategory(){
         return productService.getAllCategory();
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handleProductNotFoundException(Exception e){
         ErrorDto edto = new ErrorDto();
         edto.setMessage(e.getMessage());
         return new ResponseEntity<>(edto, HttpStatus.NOT_FOUND);
    }
}


