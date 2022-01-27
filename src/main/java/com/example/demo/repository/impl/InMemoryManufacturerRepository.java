package com.example.demo.repository.impl;

import com.example.demo.data.DataHolder;
import com.example.demo.model.Manufacturer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryManufacturerRepository {
    public List<Manufacturer> findAll(){
        return DataHolder.manufacturers;
    }
    public Optional<Manufacturer> findById (Long id){
        return DataHolder.manufacturers.stream().filter(m->m.getId().equals(id)).findFirst();
    }
    public Optional<Manufacturer> save(String name, String country){
        Manufacturer manufacturer = new Manufacturer(name,country);
        DataHolder.manufacturers.add(manufacturer);
        return Optional.of(manufacturer);
    }
    public void deleteById (Long id){
        DataHolder.manufacturers.removeIf(m->m.getId().equals(id));
    }
}
