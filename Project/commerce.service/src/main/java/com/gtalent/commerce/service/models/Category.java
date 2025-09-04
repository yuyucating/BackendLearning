package com.gtalent.commerce.service.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="category_id")
    private int id;
    @Column(name="category_name", nullable=false)
    private String name;
    @Column(name="is_deleted", nullable=false)
    private boolean is_deleted = false;

    //一個 category 會對應多個 products
    @OneToMany(mappedBy="categories", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Product> products;
}
