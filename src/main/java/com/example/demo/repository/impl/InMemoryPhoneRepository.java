package com.example.demo.repository.impl;

import com.example.demo.data.DataHolder;
import com.example.demo.model.Category;
import com.example.demo.model.Manufacturer;
import com.example.demo.model.Phone;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryPhoneRepository {
    public List<Phone> findAll(){
        return DataHolder.phones;
    }
    public Optional<Phone> findById (Long id){
        return DataHolder.phones.stream().filter(p->p.getId().equals(id)).findFirst();
    }
    public Optional<Phone> findByName (String name){
        return DataHolder.phones.stream().filter(p->p.getName().equals(name)).findFirst();
    }
    public void deleteById (Long id){
        DataHolder.phones.removeIf(p->p.getId().equals(id));
    }
    public void deleteByName (String name){
        DataHolder.phones.removeIf(p->p.getName().equals(name));
    }
    public Optional<Phone> save (String name, Double price,String image,Integer weight, Double size, String resolution, String OS, String cpu, String memory, String camera, String battery,Category category, Manufacturer manufacturer){
        DataHolder.phones.removeIf(p->p.getName().equals(name));
        Phone phone = new Phone(name,price,image,weight,size,resolution,OS,cpu,memory,camera,battery,manufacturer,category);
        DataHolder.phones.add(phone);
        return Optional.of(phone);
    }
}
