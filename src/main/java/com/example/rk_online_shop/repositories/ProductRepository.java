package com.example.rk_online_shop.repositories;

import com.example.rk_online_shop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product product);
    Product findByTitle(String title);
    Optional<Product> findById(Long id);
    List<Product> findAll();
    void deleteProductById(Long id);

//    Native query to get all product by category
    @Query(value = "select p.* from product p join category c ON p.category_id = c.id where c.title = :category", nativeQuery = true)
    List<Product> getAllProductBycategory(@Param("category") String category);
}
