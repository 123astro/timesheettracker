package com.timesheettracker.timesheettracker.controllers;

import com.timesheettracker.timesheettracker.models.Attorney;
import com.timesheettracker.timesheettracker.models.Client;
import com.timesheettracker.timesheettracker.repositories.ClientRepository;
import com.timesheettracker.timesheettracker.repositories.AttorneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController

@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AttorneyRepository attorneyRepository;

    @PostMapping("/{attorneyID}/addClient")
    public ResponseEntity<?> createNewClient(@PathVariable("attorneyID") Long id, @RequestBody Client newClient) {
        Optional<Attorney> maybeAttorney = attorneyRepository.findById(id);
        if (maybeAttorney.isEmpty()) {
            return new ResponseEntity<>(("Not Found"), HttpStatus.NOT_FOUND);
        }
        newClient.setAttorney(maybeAttorney.get());
        Client client = clientRepository.save(newClient);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClientByID(@PathVariable Long id) {
        Optional<Client> maybeClient = clientRepository.findById(id);
        if (maybeClient.isEmpty()) {
            return new ResponseEntity<>(("Not Found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(maybeClient.get(), HttpStatus.OK);
    }

    @GetMapping("/attorney/{attorneyId}")
    public ResponseEntity<?> getClientsByAttorneyID(@PathVariable ("attorneyId") Long id) {
        List<Client> clients= clientRepository.findAllByAttorney_id(id);
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/deleteClient")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        clientRepository.deleteById(id);
        return new ResponseEntity<>( "Deleted", HttpStatus.OK);
    }


    // ADD CODE =>  IF client already exist, attorney can not use that client.


}
