package com.gtalent.commerce.service.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) //AUTO_INCREMENT
    @Column(name="product_id")
    private int id;
    @Column(name="product_name", nullable=false, unique=true)
    private String name;
    @Column(name="description")
    private String description;

    // 好多個 Product 有機會對到同一個 Category
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category categories;

    @Column(name="width")
    private float width;
    @Column(name="height")
    private float height;
    @Column(name="price", nullable=false)
    private double price = 0;
    @Column(name="stock", nullable=false)
    private int stock = 0;
    @Column(name="sales")
    private int sales;
    @Column(name="imge_url")
    private String imgeUrl;
    @Column(name="image_thumbnail")
    private String imageThumbnail;
    @Column(name="is_deleted")
    private boolean is_deleted=false;
}
