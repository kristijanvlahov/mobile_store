package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Accessory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String image;

    private Double price;

    @ManyToOne
    private Manufacturer manufacturer;

    @ManyToOne
    private Category category;

    public Accessory () {};

    public Accessory(String name, String image, Double price, Manufacturer manufacturer, Category category) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.manufacturer = manufacturer;
        this.category = category;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
