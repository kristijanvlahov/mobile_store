package com.example.demo.service.impl;

import com.example.demo.model.Favourites;
import com.example.demo.model.Phone;
import com.example.demo.model.User;
import com.example.demo.model.exceptions.PhoneNotFoundException;
import com.example.demo.repository.FavouritesRepository;
import com.example.demo.repository.PhoneRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FavouritesService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavouritesServiceImpl implements FavouritesService {
    private final FavouritesRepository favouritesRepository;
    private final UserRepository userRepository;
    private final PhoneRepository phoneRepository;

    public FavouritesServiceImpl(FavouritesRepository favouritesRepository, UserRepository userRepository, PhoneRepository phoneRepository) {
        this.favouritesRepository = favouritesRepository;
        this.userRepository = userRepository;
        this.phoneRepository = phoneRepository;
    }

    @Override
    public List<Favourites> findAll() {
        return this.favouritesRepository.findAll();
    }

    @Override
    public Optional<Favourites> findById(Long id) {
        return this.favouritesRepository.findById(id);
    }

    @Override
    public Optional<Favourites> save(Long phoneId, String username) {
        Phone phone = this.phoneRepository.findById(phoneId)
                .orElseThrow(()-> new PhoneNotFoundException(phoneId));
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException(username));
        return Optional.of(this.favouritesRepository.save(new Favourites(phone,user)));
    }

    @Override
    public void deleteById(Long id) {
        this.favouritesRepository.deleteById(id);
    }

}
