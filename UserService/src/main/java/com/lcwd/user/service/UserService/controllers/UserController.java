package com.lcwd.user.service.UserService.controllers;


import com.lcwd.user.service.UserService.Entities.User;
import com.lcwd.user.service.UserService.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.EventRecodingLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    Logger logger= LoggerFactory.getLogger(UserController.class);

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
    @CircuitBreaker(name = "RATING-HOTEL-BREAKER",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable("userId") String userId)
    {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    //fallback method for circuit breaker
    public ResponseEntity<User> ratingHotelFallback(String userId,Exception e){
        logger.info("Fallback is executed because service is down: ",e.getMessage());
        User user=User.builder()
                .email("dummy@email.com")
                .name("dummuy")
                .userId("0000")
                .about("This is dummy user").build();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUser());
    }


}
