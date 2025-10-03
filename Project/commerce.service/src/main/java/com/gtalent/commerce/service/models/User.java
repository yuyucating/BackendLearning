package com.gtalent.commerce.service.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) //AUTO_INCREMENT
    @Column(name="user_id")
    private int id;
    @Column(name="first_name", nullable=false)
    private String firstName;
    @Column(name="last_name", nullable=false)
    private String lastName;
    @Column(name="email", nullable=false, unique=true)
    private String email;
    @Column(name="birthday", nullable=true)
    private LocalDate birthday;
    @Column(name="address", nullable=false)
    private String address;
    @Column(name="city", nullable=false)
    private String city;
    @Column(name="state", nullable=false)
    private String state;
    @Column(name="zipcode", nullable=false)
    private String zipcode;
    @Column(name="password", nullable=false)
    private String password;
    @Column(name="role", nullable=false)
    private String role;
    @Column(name="has_newsletter", nullable=false)
    private boolean hasNewsletter = true;

    @OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.EAGER) //對應 UserSegment 裡面的 user 資料
    private List<UserSegment> userSegments = new ArrayList<>();

    @Column(name="last_login_time")
    private LocalDateTime lastLoginTime;
    @Column(name="is_deleted", nullable=false)
    private boolean isDeleted = false;

    @OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.EAGER) //對應 UserSegment 裡面的 user 資料
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Review> review = new ArrayList<>();

    public String getFullName(){
        return this.firstName+" "+this.lastName;
    }
    
}
