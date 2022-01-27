package com.example.demo.model.dto;

import lombok.Data;

@Data
public class PhoneDto {
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

    private Long category;

    private Long manufacturer;

    public PhoneDto () {};

    public PhoneDto(String name, Double price, String image, Integer weight, Double size, String resolution,
                    String OS, String cpu, String memory, String camera, String battery,
                    Long category, Long manufacturer) {
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
        this.category = category;
        this.manufacturer = manufacturer;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
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

    public String getOS() {
        return OS;
    }

    public void setOS(String OS) {
        this.OS = OS;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public Long getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Long manufacturer) {
        this.manufacturer = manufacturer;
    }
}
