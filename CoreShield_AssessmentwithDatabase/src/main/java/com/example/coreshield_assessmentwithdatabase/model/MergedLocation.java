package com.example.coreshield_assessmentwithdatabase.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MergedLocation {
    private Location location;
    private Metadata metadata;
}

