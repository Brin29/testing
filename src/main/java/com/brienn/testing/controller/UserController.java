package com.brienn.testing.controller;

import com.brienn.testing.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.brienn.testing.services.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getAllUser();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User newUser){
        return userService.addUser(newUser);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User newUser){
        return userService.updateUser(id, newUser);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

}
