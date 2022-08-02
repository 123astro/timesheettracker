package com.timesheettracker.timesheettracker.controllers;


import com.timesheettracker.timesheettracker.models.Action;
import com.timesheettracker.timesheettracker.models.Matter;
import com.timesheettracker.timesheettracker.repositories.ActionRepository;
import com.timesheettracker.timesheettracker.repositories.AttorneyRepository;
import com.timesheettracker.timesheettracker.repositories.ClientRepository;
import com.timesheettracker.timesheettracker.repositories.MatterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    //adds time to a matter {"actionName": "phone call",  "matter": { "id": 4 }}
    @PostMapping("/start/actionID/{actionID}")
    public ResponseEntity<?> startTimer(@RequestBody Action newAction,
                                             @PathVariable ("actionID") Long id ) throws InterruptedException {
        //if id already exist, add time to it
        Optional <Action> dataBaseTime = actionRepository.findById(id);
        Long oldTime2 =  dataBaseTime.get().time;
        System.out.println("Old time " + oldTime2);
        Action action = actionRepository.save(newAction);
        System.out.println(actionRepository.getReferenceById(action.getId()));
        Long addTime = action.startTimer();
        System.out.println("add time: " + addTime);
        System.out.println("Old time " + dataBaseTime);
        action.setTime(oldTime2 + addTime);
        System.out.println(actionRepository.getReferenceById(action.getId()));
        actionRepository.save(action);
        return new ResponseEntity<>(action, HttpStatus.CREATED);
    }


    @PostMapping("/start")
    public ResponseEntity<Action> startTimer(@RequestBody Action newAction) throws InterruptedException {
        //if id already exist, add time to it
        Action action = actionRepository.save(newAction);
        Long addTime = action.startTimer();
        System.out.println("add time: " + addTime);
        actionRepository.save(newAction);
        return new ResponseEntity<>(action, HttpStatus.CREATED);
    }


    public Long getSqlTime(Long id) {
        return actionRepository.getReferenceById(id).getTime();
    }

//    @PostMapping("/start/{id}")
//    public ResponseEntity<?> addTime(@PathVariable Long id) throws InterruptedException {
//        Action action = new Action();
//        Long mysqlTime = actionRepository.getReferenceById(id).getTime();
//        System.out.println(mysqlTime);
//        action.startTimer();
//        System.out.println(action.getTime());
//        actionRepository.getReferenceById(id).setTime((mysqlTime + action.getTime()));
//        actionRepository.save(action);
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

//    @PostMapping("/stop")
//    public ResponseEntity<Action> stopTimer(@RequestBody Action newAction){
//        Action action = actionRepository.save(newAction);
//        action.stopTimer();
//        return new ResponseEntity<>(action, HttpStatus.CREATED);
//    }


}
