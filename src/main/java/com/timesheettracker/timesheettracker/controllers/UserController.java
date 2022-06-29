package com.timesheettracker.timesheettracker.controllers;


import com.timesheettracker.timesheettracker.models.User;
import com.timesheettracker.timesheettracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController

@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/test")
    public ResponseEntity<?> testRoute() { return new ResponseEntity<>("Hello" , HttpStatus.OK);}



    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User newUser){
        User user = userRepository.save(newUser);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }


    @GetMapping("/")
    public ResponseEntity<?> getAllUsers(){
        List<User> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneUser(@PathVariable Long id){

        Optional<User> maybeUser = userRepository.findById(id);

        if (maybeUser.isEmpty()) {
            return new ResponseEntity<>(("Not Found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(maybeUser.get(), HttpStatus.OK);
    }
}
