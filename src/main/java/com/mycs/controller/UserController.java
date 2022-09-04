package com.mycs.controller;

import com.mycs.entities.User;
import com.mycs.exception.UserNotFoundException;
import com.mycs.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public String create(@RequestBody User user) {
        User newUser = userService.save(user);
        return String.format("User %s created.", newUser.getName());
    }

    @PostMapping("/validate")
    public ResponseEntity<String> validateUser(@RequestBody User user) {
        User newUser = userService.getUserByName(user.getName());

        if (newUser != null && newUser.getPassword().equals(user.getPassword())) {
            return new ResponseEntity<>(String.format("User %s successfully authenticated.", newUser.getName()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Wrong credentials.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.PARTIAL_CONTENT) //206
    public User getUserByID(@PathVariable Integer id) throws UserNotFoundException {
        try {
            return userService.getUserByID(id);
        } catch (NoSuchElementException e){
            throw new UserNotFoundException(String.format("Can not find user with ID: {%d}", id));
        }
    }
}
