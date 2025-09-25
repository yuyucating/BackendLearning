package com.gtalent.commerce.service.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.gtalent.commerce.service.enums.OrderStatus;

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
import lombok.ToString;

@Entity
@Table(name="orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"totals", "orderProducts", "user"})
public class Order {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="order_id", nullable=false)
    private int id;
    @Column(name="date", nullable=false)
    private LocalDateTime datetime = LocalDateTime.now();
    @Column(name="status", nullable=false)
    private OrderStatus status;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @Column(name="is_returned", nullable=false)
    private boolean isReturned = false;
    @Column(name="delivery_fee", nullable=false)
    private BigDecimal deliveryFee = BigDecimal.ZERO;
    @Column(name="tax", nullable=false)
    private BigDecimal tax = BigDecimal.ZERO;

    @OneToMany(mappedBy="order", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    public BigDecimal getTotals(){
        BigDecimal totals = BigDecimal.ZERO;
        for(OrderProduct op:this.orderProducts){
            totals = totals.add(op.getProduct().getPrice().multiply(new BigDecimal(op.getQty())));
        }
        totals = totals.add(this.deliveryFee);
        totals = totals.add(this.deliveryFee.multiply(new BigDecimal("0.05")));

        return totals;
    }

    public BigDecimal getTotals(List<OrderProduct> ops, BigDecimal deliveryFee){
        BigDecimal totals = BigDecimal.ZERO;
        for(OrderProduct op:ops){
            totals = totals.add(op.getProduct().getPrice().multiply(new BigDecimal(op.getQty())));
        }
        totals = totals.add(deliveryFee);
        totals = totals.add(deliveryFee.multiply(new BigDecimal("0.05")));

        return totals;
    }

    public BigDecimal getSum(){
        BigDecimal sum = BigDecimal.ZERO;
        for(OrderProduct op:this.orderProducts){
            sum = sum.add(op.getProduct().getPrice().multiply(new BigDecimal(op.getQty())));
        }

        return sum;
    }


}
