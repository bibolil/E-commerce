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
    public List<User> getUsers()
    {
        return userRepository.findAll();
    }

    public void addNewUser(User user) {
        Optional<User> userByEmail=userRepository.findUserByEmail(user.getEmail());
        if (userByEmail.isPresent())
        {
            throw new IllegalStateException("Email being used");
        }
        userRepository.save(user);
    }

    public void deleteUser(long id) {
       if(userRepository.existsById(id))
       {
           userRepository.deleteById(id);
       }
       else {
           throw new IllegalStateException("User id "+id+" does not exists");
       }

    }

    public User updateUserById(Long userId, User updatedUser) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        // Update user fields
        user.setName(updatedUser.getName());
        user.setDob(updatedUser.getDob());
        user.setEmail(updatedUser.getEmail());
        user.setAddressLine(updatedUser.getAddressLine());
        user.setMobileNum(updatedUser.getMobileNum());

        return userRepository.save(user);
    }
}
