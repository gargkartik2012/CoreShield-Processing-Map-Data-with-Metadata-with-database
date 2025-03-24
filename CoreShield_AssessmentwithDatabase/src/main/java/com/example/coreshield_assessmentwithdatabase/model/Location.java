package com.example.coreshield_assessmentwithdatabase.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "locations")
public class Location {
    @Id
    private String id;
    private double latitude;
    private double longitude;
}

