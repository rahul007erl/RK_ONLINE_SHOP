package com.example.rk_online_shop.repositories;

import com.example.rk_online_shop.model.Category;
import com.example.rk_online_shop.repositories.projections.CategoryProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category save(Category category);
    Category findByTitle(String title);

//    Naive query to get all category
    @Query(value = "select c.title from category c", nativeQuery = true)
    List<CategoryProjection> getAllCategory();
}
