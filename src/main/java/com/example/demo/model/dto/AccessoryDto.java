package com.example.demo.model.dto;

import lombok.Data;

@Data
public class AccessoryDto {
    private String username;

    private Long accessory;

    public AccessoryDto () {};

    public AccessoryDto(String username, Long accessory) {
        this.username = username;
        this.accessory = accessory;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getAccessory() {
        return accessory;
    }

    public void setAccessory(Long accessory) {
        this.accessory = accessory;
    }
}
