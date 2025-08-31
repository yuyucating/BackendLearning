package com.gtalent.commerce.service.models;

import java.time.LocalDateTime;

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
@Table(name="user_segments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSegment {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) //AUTO_INCREMENT
    @Column(name="user_segment_id")
    private int id;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="segment_id", nullable=false)
    private Segment segment;

    @Column(name="created_at", nullable=false)
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(name="deleted_at")
    private LocalDateTime deletedAt;
}
