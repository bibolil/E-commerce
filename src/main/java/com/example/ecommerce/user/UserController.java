package com.example.ecommerce.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path="user")
@PreAuthorize("hasRole('ADMIN')")
public class UserController {
    private final UserService userService;

    @PreAuthorize("hasAuthority('admin:read')")
    @GetMapping(path = "getUsers")
    public List<User> getUsers()
    {
      return this.userService.getUsers();
    }

    @PreAuthorize("hasAuthority('admin:create')")
    @PostMapping(path="registerUser")
    public void registerNewUser(@RequestBody User user)
    {
        this.userService.addNewUser(user);
    }

    @PreAuthorize("hasAuthority('admin:delete')")
    @DeleteMapping(path = "deleteUser/{userId}")
    public void deleteUser(@PathVariable("userId") long id){
        this.userService.deleteUser(id);
    }

    @PreAuthorize("hasAuthority('admin:update')")
    @PutMapping(path="updateUser/{userId}")
    public void updateUser(
            @PathVariable("userId") long userId,
            @RequestBody User updatedUser)
    {
        this.userService.updateUserById(userId,updatedUser);
    }

}
