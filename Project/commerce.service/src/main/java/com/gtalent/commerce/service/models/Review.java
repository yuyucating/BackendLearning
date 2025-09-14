package com.gtalent.commerce.service.models;

import java.time.LocalDate;

import com.gtalent.commerce.service.enums.Rating;
import com.gtalent.commerce.service.enums.ReviewStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name="reviews")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="review_id")
    private int id;
    
    //一個 review 會對應一個 user
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    @Column(name="date", nullable=false)
    private LocalDate date = LocalDate.now();
    @Enumerated(EnumType.STRING)
    @Column(name="rating", nullable=false)
    private Rating rating=Rating.Five;
    @Column(name="comment")
    private String comment;
    @Enumerated(EnumType.STRING)
    @Column(name="status", nullable=false)
    private ReviewStatus status = ReviewStatus.Pending;
    @Column(name="is_deleted")
    private Boolean isDeleted=false;

}
