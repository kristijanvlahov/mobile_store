package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    private String image;

    private Integer weight;

    private Double size;

    private String resolution;

    private String OS;

    private String cpu;

    private String memory;
    
    private String camera;

    private String battery;

    @ManyToOne
    private Manufacturer manufacturer;

    @ManyToOne
    private Category category;

    public Phone () {};

    public Phone(String name, Double price, String image, Integer weight, Double size,
                 String resolution, String OS, String cpu, String memory,
                 String camera, String battery, Manufacturer manufacturer,
                 Category category)
    {
        this.name = name;
        this.price = price;
        this.image = image;
        this.weight = weight;
        this.size = size;
        this.resolution = resolution;
        this.OS = OS;
        this.cpu = cpu;
        this.memory = memory;
        this.camera = camera;
        this.battery = battery;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOS() {
        return OS;
    }

    public void setOS(String OS) {
        this.OS = OS;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }


    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
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
