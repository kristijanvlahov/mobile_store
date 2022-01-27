package com.example.demo.repository;

import com.example.demo.model.Phone;
import com.example.demo.model.ShoppingCart;
import com.example.demo.model.User;
import com.example.demo.model.enumerations.ShoppingCartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
    Optional<ShoppingCart> findByUserUsernameAndStatus(String username, ShoppingCartStatus status);
    Optional<ShoppingCart> findByUserAndStatus(User user, ShoppingCartStatus status);
    List<Phone> findAllByUserUsername(String username);
    List<Phone> findByUser (String user);
}
