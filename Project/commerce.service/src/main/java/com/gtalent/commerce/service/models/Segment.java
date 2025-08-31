package com.gtalent.commerce.service.models;

import java.util.List;

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
@Table(name="segments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Segment {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) //AUTO_INCREMENT
    @Column(name="segment_id")
    private int id;
    @Column(name="segment_name", nullable=false)
    private String name;

    @OneToMany(mappedBy="segment", fetch=FetchType.LAZY)
    private List<UserSegment> userSegments;
}
