package com.example.demo.model.dto;

import lombok.Data;

@Data
public class FavouritesDto {
    private Long phone;
    private String username;
    private String name;
    private Double price;
    private String image;

    public FavouritesDto () {};

    public FavouritesDto(Long phone, String username, String name, Double price,String image) {
        this.phone = phone;
        this.username = username;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
