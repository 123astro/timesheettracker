package com.timesheettracker.timesheettracker.controllers;


import com.timesheettracker.timesheettracker.models.Action;
import com.timesheettracker.timesheettracker.repositories.ActionRepository;
import com.timesheettracker.timesheettracker.repositories.AttorneyRepository;
import com.timesheettracker.timesheettracker.repositories.ClientRepository;
import com.timesheettracker.timesheettracker.repositories.MatterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController

@RequestMapping("/api/action")



public class ActionController {

    @Autowired
    private AttorneyRepository attorneyRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private MatterRepository matterRepository;

    @Autowired
    private ActionRepository actionRepository;

    @PostMapping("/start")
    public ResponseEntity<Action> startTimer(@RequestBody Action newAction){
        Action action = actionRepository.save(newAction);
        action.startTimer();
        return new ResponseEntity<>(action, HttpStatus.CREATED);
    }

//    @PostMapping("/stop")
//    public ResponseEntity<Action> stopTimer(@RequestBody Action newAction){
//        Action action = actionRepository.save(newAction);
//        action.stopTimer();
//        return new ResponseEntity<>(action, HttpStatus.CREATED);
//    }


}
