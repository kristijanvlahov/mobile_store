package com.example.demo.model.dto;

import com.example.demo.model.Phone;
import com.example.demo.model.User;
import lombok.Data;


@Data
public class ShoppingCartDto {
    private String username;

    private Long phone;

    public ShoppingCartDto () {};

    public ShoppingCartDto(String username, Long phone) {
        this.username = username;
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }
}
