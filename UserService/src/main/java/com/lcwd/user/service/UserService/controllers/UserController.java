package com.lcwd.user.service.UserService.controllers;


import com.lcwd.user.service.UserService.Entities.User;
import com.lcwd.user.service.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(userService.saveUser(user));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getSingleUser(@PathVariable("userId") String userId)
    {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUser());
    }


}
