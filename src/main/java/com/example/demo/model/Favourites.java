package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Favourites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Phone phone;

    @ManyToOne
    private User user;

    public Favourites () {};

    public Favourites( Phone phone, User user) {
        this.phone = phone;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
