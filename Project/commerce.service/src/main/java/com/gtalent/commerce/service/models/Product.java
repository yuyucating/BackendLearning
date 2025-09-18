package com.gtalent.commerce.service.models;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="category_id")
    private Category category;

    @Column(name="width")
    private float width;
    @Column(name="height")
    private float height;
    @Column(name="price", nullable=false)
    private BigDecimal price;
    @Column(name="stock", nullable=false)
    private int stock = 0;
    @Column(name="sales")
    private int sales;
    @Column(name="image_url")
    private String imageUrl;
    @Column(name="image_thumbnail", nullable=false)
    private String imageThumbnail;
    @Column(name="is_deleted",nullable=false)
    private boolean isDeleted=false;

    @OneToMany(mappedBy="product", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<OrderProduct> orderProducts;

    @OneToMany(mappedBy="product", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Review> review;

}
