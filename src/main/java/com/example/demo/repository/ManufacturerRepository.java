package com.example.demo.repository;

import com.example.demo.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer,Long> {
    List<Manufacturer> findAllByCountryLike(String text);
}
