package com.example.rk_online_shop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product  extends BaseModel{
    private String title;
    private String description;
    private double price;
    private String imageUrl;
    @ManyToOne
    private Category category;
}
