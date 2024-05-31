package com.example.rk_online_shop.dto;

import com.example.rk_online_shop.model.Category;
import com.example.rk_online_shop.model.Product;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;

    public Product toProduct(){
        Product p = new Product();
        p.setId(id);
        p.setTitle(title);
        p.setPrice(price);
        p.setDescription(description);
        p.setImageUrl(image);
        Category cat = new Category();
        cat.setTitle(category);
        p.setCategory(cat);
        return p;
    }
}
