package com.example.demo.service.impl;

import com.example.demo.model.Accessory;
import com.example.demo.model.Phone;
import com.example.demo.model.ShoppingCart;
import com.example.demo.model.User;
import com.example.demo.model.dto.ChargeRequest;
import com.example.demo.model.enumerations.ShoppingCartStatus;
import com.example.demo.model.exceptions.*;
import com.example.demo.repository.ShoppingCartRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AccessoryService;
import com.example.demo.service.PaymentService;
import com.example.demo.service.PhoneService;
import com.example.demo.service.ShoppingCartService;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final PhoneService phoneService;
    private final AccessoryService accessoryService;
    private final PaymentService paymentService;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, UserRepository userRepository, PhoneService phoneService, AccessoryService accessoryService, PaymentService paymentService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.phoneService = phoneService;
        this.accessoryService = accessoryService;
        this.paymentService = paymentService;
    }
    @Override
    public List<ShoppingCart> findAll() {
        return this.shoppingCartRepository.findAll();
    }

    @Override
    public List<Phone> listAllProductsInShoppingCart(Long cartId) {
        if(!this.shoppingCartRepository.findById(cartId).isPresent()){
            throw new ShoppingCartNotFoundException(cartId);
        }
        return this.shoppingCartRepository.findById(cartId).get().getPhones();
    }

    @Override
    public List<Accessory> listAllAccessoriesInShoppingCart(Long id) {
        if(!this.shoppingCartRepository.findById(id).isPresent()){
            throw new ShoppingCartNotFoundException(id);
        }
        return this.shoppingCartRepository.findById(id).get().getAccessories();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(()->new UserNotFoundException(username));

        return this.shoppingCartRepository.findByUserAndStatus(user, ShoppingCartStatus.CREATED)
                .orElseGet(()->{
            ShoppingCart shoppingCart = new ShoppingCart(user);
            return this.shoppingCartRepository.save(shoppingCart);
        });
    }

    @Override
    @Transactional
    public Optional<ShoppingCart> save(String username, Long productId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        Phone phone = this.phoneService.findById(productId)
                .orElseThrow(()->new PhoneNotFoundException(productId));
        if(shoppingCart.getPhones()
                .stream().filter(i->i.getId().equals(productId))
                .collect(Collectors.toList()).size()>0)
            throw new ProductAlreadyInShoppingCartException(productId,username);
        shoppingCart.getPhones().add(phone);
        return Optional.of(this.shoppingCartRepository.save(shoppingCart));
    }

    @Override
    @Transactional
    public Optional<ShoppingCart> addAccessory(String username, Long productId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        Accessory accessory = this.accessoryService.findById(productId)
                .orElseThrow(()->new PhoneNotFoundException(productId));
        if(shoppingCart.getAccessories()
                .stream().filter(i->i.getId().equals(productId))
                .collect(Collectors.toList()).size()>0)
            throw new ProductAlreadyInShoppingCartException(productId,username);
        shoppingCart.getAccessories().add(accessory);
        return Optional.of(this.shoppingCartRepository.save(shoppingCart));
    }

    @Override
    @Transactional
    public ShoppingCart checkoutShoppingCart(User userId, ChargeRequest chargeRequest) {
        ShoppingCart shoppingCart = this.shoppingCartRepository
                .findByUserAndStatus(userId, ShoppingCartStatus.CREATED)
                .orElseThrow(() -> new ShoppingCartIsNotActiveException(userId));

        List<Phone> phones = shoppingCart.getPhones();
        List<Accessory> accessories = shoppingCart.getAccessories();
        float price = 0;

        for (Phone phone : phones) {
            price += phone.getPrice();
        }
        for (Accessory accessory : accessories) {
            price += accessory.getPrice();
        }
        Charge charge = null;
        try {
            charge = this.paymentService.pay(chargeRequest);
        } catch (CardException | APIException | AuthenticationException | APIConnectionException | InvalidRequestException e) {
            throw new TransactionFailedException(userId, e.getMessage());
        }

        shoppingCart.setPhones(phones);
        shoppingCart.setAccessories(accessories);
        shoppingCart.setStatus(ShoppingCartStatus.FINISHED);
        return this.shoppingCartRepository.save(shoppingCart);

    }

    @Override
    @Transactional
    public ShoppingCart removeProductFromShoppingCart(String username,Long productId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username) ;
        shoppingCart.setPhones(
                shoppingCart.getPhones()
                        .stream()
                        .filter(product -> !product.getId().equals(productId))
                        .collect(Collectors.toList())
        );
        return this.shoppingCartRepository.save(shoppingCart);

    }

    @Override
    @Transactional
    public ShoppingCart removeAccessoryFromShoppingCart(String username,Long productId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username) ;
        shoppingCart.setAccessories(
                shoppingCart.getAccessories()
                        .stream()
                        .filter(product -> !product.getId().equals(productId))
                        .collect(Collectors.toList())
        );
        return this.shoppingCartRepository.save(shoppingCart);

    }

    @Override
    public Optional<ShoppingCart> findById(Long id) {
        return this.shoppingCartRepository.findById(id);
    }

    @Override
    public List<Phone> findByUser(String user) {
        return this.shoppingCartRepository.findByUser(user);
    }

    @Override
    public List<Phone> listAllPhones(String user) {
        ShoppingCart cart = this.getActiveShoppingCart(user);
        return cart.getPhones();
    }

    @Override
    public List<Accessory> listAllAccessories(String user) {
        ShoppingCart cart = this.getActiveShoppingCart(user);
        return cart.getAccessories();
    }
}
