package com.example.coreshield_assessmentwithdatabase.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "metadata")
public class Metadata {
    @Id
    private String id;

    @Column(nullable = true)
    private String type;

    @Column(nullable = true)
    private Double rating;

    @Column(nullable = true)
    private Integer reviews;
}

