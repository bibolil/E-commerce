package com.example.ecommerce.cart;


import com.example.ecommerce.cart.Cart;
import com.example.ecommerce.cart.CartService;
import com.example.ecommerce.item.Item;
import com.example.ecommerce.user.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
@RequestMapping(path = "cart")
//@PreAuthorize("hasAnyRole('ADMIN','USER')")
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService=cartService;
    }


    @GetMapping(path="getCarts")
    public List<Cart> getCarts() {
        System.out.println("this is from getting carts ");
        return this.cartService.getCarts();}

    @PostMapping("addItem/{userId}")
    public ResponseEntity<String> addItemToCart(@PathVariable Long userId, @RequestBody Item item) {
        System.out.println("this is from getting cart by user ");
        try {
            cartService.addItemToCart(userId, item);
            return ResponseEntity.ok("Item added to cart successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to add item to cart: " + e.getMessage());
        }
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<Cart> getCartForUser(@PathVariable Long userId) {
        System.out.println("this is from getting cart by user ");
        Optional<Cart> optionalCart = cartService.getCartForUser(userId);

        if (optionalCart.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Cart cart = optionalCart.get();
        return ResponseEntity.ok(cart);
    }

}
