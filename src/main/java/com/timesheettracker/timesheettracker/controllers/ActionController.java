package com.timesheettracker.timesheettracker.controllers;


import com.timesheettracker.timesheettracker.models.Action;
import com.timesheettracker.timesheettracker.models.Attorney;
import com.timesheettracker.timesheettracker.repositories.ActionRepository;
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
    @PostMapping("/actionID/{actionID}/attorneyID/{attorneyID}")
    public ResponseEntity<?> startTimer(@RequestBody Action newAction,
                                             @PathVariable ("actionID") Long id,
                                        @PathVariable ("attorneyID") Long aId) throws InterruptedException {

        Action action = actionRepository.save(newAction);
        Long dataBaseTime = actionRepository.findById(id).get().getTime();
        Long addTime = action.startTimer();
        action.setTime(dataBaseTime + addTime);
        Optional<Attorney> maybeAttorney = attorneyRepository.findById(aId);
        System.out.println(maybeAttorney);
        action.setAttorney(maybeAttorney.get());
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


   // public Long getSqlTime(Long id) {
//        return actionRepository.getReferenceById(id).getTime();
//    }

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

    @GetMapping("/")
    public ResponseEntity<?> getAllActions() {
        List <Action> actions = actionRepository.findAll();
        return new ResponseEntity<>(actions, HttpStatus.OK);
    }

    @GetMapping("/attorney/{attorneyId}")
    public ResponseEntity<?> getActionByAttorneyId(@PathVariable ("attorneyId") Long id) {
        List<Action>  actions = actionRepository.findAllByAttorney_id(id);

        return new ResponseEntity<>(actions, HttpStatus.OK);
    }

    @GetMapping("/matter/{matterId}")
    public ResponseEntity<?> getActionByMatterId(@PathVariable ("matterId") Long id) {
        List<Action>  actions = actionRepository.findAllByMatter_id(id);
        String result = actions.get(0).getMatter().getClient().getCompanyName();
        System.out.println(result);
        return new ResponseEntity<>(actions, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAllActions(@PathVariable Long id) {
        actionRepository.deleteById(id);
        return new ResponseEntity<>( "Deleted", HttpStatus.OK);
    }





}
