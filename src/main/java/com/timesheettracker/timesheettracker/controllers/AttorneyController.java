package com.timesheettracker.timesheettracker.controllers;


import com.timesheettracker.timesheettracker.models.Attorney;
import com.timesheettracker.timesheettracker.models.Client;
import com.timesheettracker.timesheettracker.repositories.AttorneyRepository;
import com.timesheettracker.timesheettracker.repositories.ClientRepository;
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
    private AttorneyRepository attorneyRepository;

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/test")
    public ResponseEntity<?> testRoute() { return new ResponseEntity<>("We are connected to the timesheets database!" ,
            HttpStatus.OK);}

    @PostMapping("/")
    public ResponseEntity<Attorney> createUser(@RequestBody Attorney newUser){
        Attorney user = attorneyRepository.save(newUser);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }


    @GetMapping("/")
    public ResponseEntity<?> getAllUsers(){
        List<Attorney> users = attorneyRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneUser(@PathVariable Long id){

        Optional<Attorney> maybeUser = attorneyRepository.findById(id);

        if (maybeUser.isEmpty()) {
            return new ResponseEntity<>(("Not Found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(maybeUser.get(), HttpStatus.OK);
    }

}
