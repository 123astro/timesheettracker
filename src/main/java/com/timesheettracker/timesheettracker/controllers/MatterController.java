package com.timesheettracker.timesheettracker.controllers;

import com.timesheettracker.timesheettracker.models.Client;
import com.timesheettracker.timesheettracker.models.Matter;
import com.timesheettracker.timesheettracker.repositories.ClientRepository;
import com.timesheettracker.timesheettracker.repositories.MatterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController

@RequestMapping("/api/matter")

public class MatterController {


    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private MatterRepository matterRepository;

    @PostMapping("/{clientID}")
    public ResponseEntity<?> createNewClient(@PathVariable("clientID") Long id, @RequestBody Matter newMatter){
        Optional<Client> maybeClient = clientRepository.findById(id);
        if (maybeClient.isEmpty()) {
            return new ResponseEntity<>(("Not Found"), HttpStatus.NOT_FOUND);
        }
        newMatter.setClient(maybeClient.get());
        Matter matter = matterRepository.save(newMatter);
        return new ResponseEntity<>(matter, HttpStatus.OK);
    }
}
