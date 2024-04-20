package com.example.ecommerce.cart;

import com.example.ecommerce.item.Item;
import com.example.ecommerce.user.User;
import com.example.ecommerce.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    @Autowired
    public CartService(CartRepository cartRepository,UserRepository userRepository)
    {
        this.cartRepository=cartRepository;
        this.userRepository = userRepository;
    }

    public List<Cart> getCarts()
    {
        return cartRepository.findAll();
    }

    public Cart addItemToCart(Long userId, Item item) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            // User not found, handle accordingly (e.g., throw exception)
            return null;
        }

        // Check if the user already has a cart
        Cart cart = user.getCart();
        if (cart == null) {
            // If the user doesn't have a cart, create a new one
            cart = new Cart(user);
        }

        // Add the item to the cart
        cart.addItem(item);

        // Save the cart
        return cartRepository.save(cart);
    }

    public Optional<Cart> getCartForUser(Long userId) {
        System.out.println("this is from getting cart by user in service ");
        return cartRepository.findByUserId(userId);
    }



}