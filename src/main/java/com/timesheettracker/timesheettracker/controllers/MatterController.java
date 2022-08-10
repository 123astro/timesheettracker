package com.timesheettracker.timesheettracker.controllers;

import com.timesheettracker.timesheettracker.models.Client;
import com.timesheettracker.timesheettracker.models.Matter;
import com.timesheettracker.timesheettracker.repositories.AttorneyRepository;
import com.timesheettracker.timesheettracker.repositories.ClientRepository;
import com.timesheettracker.timesheettracker.repositories.MatterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController

@RequestMapping("/api/matter")

public class MatterController {


    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private MatterRepository matterRepository;

    @Autowired
    private AttorneyRepository attorneyRepository;


    @PostMapping("/client/{clientID}/addMatter")
    public ResponseEntity<?> createNewMatterWithClient(@PathVariable("clientID") Long id,
                                                       @RequestBody Matter newMatter) {
        Optional<Client> maybeClient = clientRepository.findById(id);
        if (maybeClient.isEmpty()) {
            return new ResponseEntity<>(("Not Found"), HttpStatus.NOT_FOUND);
        }
        newMatter.setClient(maybeClient.get());
        Matter matter = matterRepository.save(newMatter);
        return new ResponseEntity<>(matter, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllMatters() {
        List<Matter> matters = matterRepository.findAll();
        return new ResponseEntity<>(matters, HttpStatus.OK);
    }

    @GetMapping("/{matterID}")
    public ResponseEntity<?> getClient(@PathVariable("matterID") Long id) {
        Optional<Matter> matters = matterRepository.findById(id);
        if (matters.isEmpty()) {
            return new ResponseEntity<>(("Not Found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(matters, HttpStatus.OK);
    }

    @GetMapping("/client/{clientID}")
    public ResponseEntity<?> getMatterFromClient(@PathVariable("clientID") Long id) {
        List<Matter> matters = matterRepository.findAllByClient_id(id);
        return new ResponseEntity<>(matters, HttpStatus.OK);
    }

    @GetMapping("/attorney/{attorneyID}")
    public ResponseEntity<?> getMattersForAttorney(@PathVariable("attorneyID") Long id) {
        List<Matter> attorneyMatters = matterRepository.findAllByClient_Attorney_id(id);
        return new ResponseEntity<>(attorneyMatters, HttpStatus.OK);
    }

}
