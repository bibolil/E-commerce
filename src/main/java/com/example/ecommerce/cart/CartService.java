package com.example.ecommerce.cart;

import com.example.ecommerce.item.Item;
import com.example.ecommerce.item.ItemRepository;
import com.example.ecommerce.user.User;
import com.example.ecommerce.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    private final ItemRepository itemRepository;

    @Autowired
    public CartService(CartRepository cartRepository,UserRepository userRepository, ItemRepository itemRepository)
    {
        this.cartRepository=cartRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
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


        Optional<Item> existingItemOptional = itemRepository.findByCode(item.getCode());
        if (existingItemOptional.isPresent()) {
            // If the item exists, associate it with the cart
            item = existingItemOptional.get(); // Get the existing item from the Optional
        } else {
            // If the item doesn't exist, save it to the database
            item = itemRepository.save(item);
        }
        // Add the item to the cart
        item.setCart(cart);
        cart.addItem(item);

        // Save the cart
        return cartRepository.save(cart);
    }

    public Optional<Cart> getCartForUser(Long userId) {
        System.out.println("this is from getting cart by user in service ");
        return cartRepository.findByUserId(userId);
    }

    public void removeItemFromCart(Long userId, String itemCode) {
        // Retrieve the user's cart
        Optional<Cart> optionalCart = cartRepository.findByUserId(userId);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            boolean removed = cart.removeItem(itemCode);
            if (!removed) {
                throw new RuntimeException("Item not found in the cart");
            }
            cartRepository.save(cart); // Save the updated cart
        } else {
            throw new RuntimeException("Cart not found for user with ID: " + userId);
        }
    }



}