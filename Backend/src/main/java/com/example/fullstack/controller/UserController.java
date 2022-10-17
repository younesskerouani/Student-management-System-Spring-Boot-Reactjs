package com.example.fullstack.controller;


import com.example.fullstack.exception.UserNotFoundException;
import com.example.fullstack.model.User;
import com.example.fullstack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    User newUser(@RequestBody User newuser){
        return userRepository.save(newuser);
    }

    @GetMapping("/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id){
        return userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newuser , @PathVariable Long id){
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(newuser.getUsername());
                    user.setName(newuser.getName());
                    user.setEmail(newuser.getEmail());
                    return userRepository.save(user);
                 }).orElseThrow(()->new UserNotFoundException(id) );
    }

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "User with id: "+id+" has been deleted successfully";
    }

}
