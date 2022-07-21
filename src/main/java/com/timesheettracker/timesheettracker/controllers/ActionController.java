package com.timesheettracker.timesheettracker.controllers;


import com.timesheettracker.timesheettracker.models.Action;
import com.timesheettracker.timesheettracker.repositories.ActionRepository;
import com.timesheettracker.timesheettracker.repositories.AttorneyRepository;
import com.timesheettracker.timesheettracker.repositories.ClientRepository;
import com.timesheettracker.timesheettracker.repositories.MatterRepository;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.sql.SQLException;
import java.util.Optional;

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
    public ResponseEntity<Action> startTimer(@RequestBody Action newAction) throws InterruptedException {
        //if id already exist, add time to it
        Action action = actionRepository.save(newAction);
        Long addTime = action.startTimer();
        System.out.println("add time: " + addTime);
        actionRepository.save(newAction);
        return new ResponseEntity<>(action, HttpStatus.CREATED);
    }


    @PostMapping("/start/{id}")
    public ResponseEntity<Action> addTime(@RequestBody Action newAction, @PathVariable Long id) throws InterruptedException {
        //if id already exist, add time to it
        Action action = actionRepository.save(newAction);
        Long sqlTime = actionRepository.getReferenceById(id).getTime();
        System.out.println("Print sqlTime ~3 :" + sqlTime);
        long addTime = action.startTimer();
        System.out.println("add time: " + addTime);
        actionRepository.getReferenceById(id).setTime(addTime);
        actionRepository.save(newAction);
        return new ResponseEntity<>(action, HttpStatus.CREATED);
    }

//    @PostMapping("/stop")
//    public ResponseEntity<Action> stopTimer(@RequestBody Action newAction){
//        Action action = actionRepository.save(newAction);
//        action.stopTimer();
//        return new ResponseEntity<>(action, HttpStatus.CREATED);
//    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getMatterTime(@PathVariable Long id) {

        Optional<Action> maybeTime = actionRepository.findById(id);

        if (maybeTime.isEmpty()) {
            return new ResponseEntity<>(("Not Found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(maybeTime.get(), HttpStatus.OK);
    }


}
