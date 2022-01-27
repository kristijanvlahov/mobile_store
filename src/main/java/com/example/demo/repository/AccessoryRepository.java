package com.example.demo.repository;

import com.example.demo.model.Accessory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccessoryRepository extends JpaRepository<Accessory,Long> {
    Optional<Accessory> findByName(String name);
    void deleteByName(String name);
}
