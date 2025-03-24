package com.example.coreshield_assessmentwithdatabase.repository;

import com.example.coreshield_assessmentwithdatabase.model.Metadata;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetadataRepository extends JpaRepository<Metadata, String> {
}
