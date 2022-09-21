package com.mycs.controller;

import com.mycs.entities.DBLog;
import com.mycs.entities.User;
import com.mycs.exception.UserNotFoundException;
import com.mycs.server.LogService;
import com.mycs.server.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
public class UserRestController {
    @Autowired
    private UserService userService;
    @Autowired
    private LogService logService;

    private final static Logger LOGGER = LoggerFactory.getLogger(MyCSController.class);

    @PostMapping
    public String create(@RequestBody User user) {
        DBLog DBLog = new DBLog();
        DBLog.setEndPoint("users");
        //TODO: DBLog.setUserName();
        DBLog.setTime(LocalDateTime.now());
        DBLog.setMessage(String.format("User %s has been created.", user.getName()));
        logService.save(DBLog);

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
    @ResponseStatus(HttpStatus.PARTIAL_CONTENT)
    public User getUserByID(@PathVariable Integer id) throws UserNotFoundException {
        try {
            return userService.getUserByID(id);
        } catch (NoSuchElementException e){
            LOGGER.error("Wrong user ID: {}", e.getMessage(), e);

            throw new UserNotFoundException(String.format("Can not find user with ID: {%d}", id));
        }
    }
}
