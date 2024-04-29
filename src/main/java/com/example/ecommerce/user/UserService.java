package com.example.ecommerce.user;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(String username) {
        return userRepository.findUserByUsername(username);
    }

    public void addNewUser(User user) {
        Optional<User> userByEmail = userRepository.findUserByEmail(user.getEmail());
        if (userByEmail.isPresent()) {
            throw new IllegalStateException("Email being used");
        }
        userRepository.save(user);
    }

    public void deleteUser(long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new IllegalStateException("User id " + id + " does not exists");
        }

    }

    public User updateUserById(Long userId, User updatedUser) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        // Update user fields
        user.setFirstname(updatedUser.getFirstname());
        user.setLastname(updatedUser.getLastname());
        return userRepository.save(user);
    }

    /*public Optional<User> getAuthenticatedUser(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        if (token != null && jwtTokenProvider.validateToken(token)) {
            String username = jwtTokenProvider.getUsername(token);
            return userRepository.findUserByUsername(username); // Assuming you have a method like this in your repository
        }
        return null;
    }*/


}
