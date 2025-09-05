package com.gtalent.commerce.service.models;

import java.time.LocalDate;

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
@Table(name="orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="order_id", nullable=false)
    private int id;
    @Column(name="date", nullable=false)
    private LocalDate date = LocalDate.now();
    @Column(name="status", nullable=false)
    private String status = "ordered";

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @Column(name="is_returned", nullable=false)
    private boolean isReturned = false;
    @Column(name="delivery_fee", nullable=false)
    private double deliveryFee = 0;
    @Column(name="tax", nullable=false)
    private double tax = 0;
}
