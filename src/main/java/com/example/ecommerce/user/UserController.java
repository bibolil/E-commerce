package com.example.ecommerce.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService=userService;
    }

    @GetMapping(path = "getUsers")
    public List<User> getUsers()
    {
      return this.userService.getUsers();
    }

    @PostMapping(path="registerUser")
    public void registerNewUser(@RequestBody User user)
    {
        this.userService.addNewUser(user);
    }
    @DeleteMapping(path = "deleteUser/{userId}")
    public void deleteUser(@PathVariable("userId") long id){
        this.userService.deleteUser(id);
    }

//    @PutMapping(path="updateUser/{userId}")
//    public void updateUser(
//            @PathVariable("userId") long userId,
//            @RequestBody User updatedUser)
//    {
//        this.userService.updateUserById(userId,updatedUser);
//    }

}
