package com.timesheettracker.timesheettracker.controllers;

import com.timesheettracker.timesheettracker.models.Attorney;
import com.timesheettracker.timesheettracker.models.Client;
import com.timesheettracker.timesheettracker.models.Matter;
import com.timesheettracker.timesheettracker.repositories.AttorneyRepository;
import com.timesheettracker.timesheettracker.repositories.ClientRepository;
import com.timesheettracker.timesheettracker.repositories.MatterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

    @PostMapping("/client/{clientID}/attorney/{attorneyID}")
    public ResponseEntity<?> createNewClient(@PathVariable("clientID") Long id,
                                             @PathVariable("attorneyID") Long aId, @RequestBody Matter newMatter){
        Optional<Client> maybeClient = clientRepository.findById(id);
        if (maybeClient.isEmpty()) {
            return new ResponseEntity<>(("Not Found"), HttpStatus.NOT_FOUND);
        }
        Optional<Attorney> maybeAttorney = attorneyRepository.findById(aId);
        if (maybeAttorney.isEmpty()) {
            return new ResponseEntity<>(("Not Found"), HttpStatus.NOT_FOUND);
        }
        newMatter.setClient(maybeClient.get());
        newMatter.setAttorney(maybeAttorney.get());
        Matter matter = matterRepository.save(newMatter);
        return new ResponseEntity<>(matter, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllMatters() {
        List<Matter> matters = matterRepository.findAll();
        return new ResponseEntity<>(matters, HttpStatus.OK);
    }

    @GetMapping("/{matterID}")
    public ResponseEntity<?> getClient(@PathVariable("matterID") Long id){
        Optional<Matter> matters = matterRepository.findById(id);
        if (matters.isEmpty()) {
            return new ResponseEntity<>(("Not Found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(matters, HttpStatus.OK);
    }


    //create a method that returns all matters associated with a client_id.
    @GetMapping("/client/{clientID}")
    public ResponseEntity<?> getMatterFromClient(@PathVariable("clientID") Long id){
       List<Matter>  matters =  matterRepository.findAllByClient_id(id);
        return new ResponseEntity<>(matters, HttpStatus.OK);
    }

    @GetMapping("/attorney/{attorneyID}")
    public ResponseEntity<?> getMattersForAttorney(@PathVariable("attorneyID") Long id){
        List<Matter>  attorneyMatters  = matterRepository.findAllByAttorney_id(id);
        return new ResponseEntity<>(attorneyMatters, HttpStatus.OK);
    }

    // I changed this to matterRep from AttorneyRep

}
