package com.example.coreshield_assessmentwithdatabase.repository;

import com.example.coreshield_assessmentwithdatabase.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, String> {
}
