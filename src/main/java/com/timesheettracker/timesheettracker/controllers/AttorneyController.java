package com.timesheettracker.timesheettracker.controllers;


import com.timesheettracker.timesheettracker.models.Attorney;
import com.timesheettracker.timesheettracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController

@RequestMapping("/api/attorney")
public class AttorneyController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/test")
    public ResponseEntity<?> testRoute() { return new ResponseEntity<>("Hello" , HttpStatus.OK);}



    @PostMapping("/")
    public ResponseEntity<Attorney> createUser(@RequestBody Attorney newUser){
        Attorney user = userRepository.save(newUser);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }


    @GetMapping("/")
    public ResponseEntity<?> getAllUsers(){
        List<Attorney> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneUser(@PathVariable Long id){

        Optional<Attorney> maybeUser = userRepository.findById(id);

        if (maybeUser.isEmpty()) {
            return new ResponseEntity<>(("Not Found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(maybeUser.get(), HttpStatus.OK);
    }
}
