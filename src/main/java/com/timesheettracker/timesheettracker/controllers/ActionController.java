package com.timesheettracker.timesheettracker.controllers;

import com.timesheettracker.timesheettracker.models.Action;
import com.timesheettracker.timesheettracker.models.Matter;
import com.timesheettracker.timesheettracker.repositories.ActionRepository;
import com.timesheettracker.timesheettracker.repositories.MatterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;



@CrossOrigin
@RestController
@RequestMapping("/api/action")

public class ActionController {

    public Long id;
    private static final String PATTERN_FORMAT = "dd.MM.yyyy hh.mm.ss";

    @Autowired
    private MatterRepository matterRepository;

    @Autowired
    private ActionRepository actionRepository;

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
        List<Action> actions = actionRepository.findAll();
        return new ResponseEntity<>(actions, HttpStatus.OK);
    }

    @GetMapping("/attorney/{attorneyId}")
    public ResponseEntity<?> getActionByAttorneyId(@PathVariable("attorneyId") Long id) {
        List<Action> actions = actionRepository.findAllByMatter_Client_Attorney_id(id);
        return new ResponseEntity<>(actions, HttpStatus.OK);
    }

    @GetMapping("/matter/{matterId}")
    public ResponseEntity<?> getActionByMatterId(@PathVariable("matterId") Long id) {
        List<Action> actions = actionRepository.findAllByMatter_id(id);
        String result = actions.get(0).getMatter().getClient().getCompanyName();
        System.out.println(result);
        return new ResponseEntity<>(actions, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAllActions(@PathVariable Long id) {
        actionRepository.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @PostMapping("/start/{matterId}/{actionName}")
    public ResponseEntity<?> newStartTimer(@PathVariable("matterId") Long id,
                                           @PathVariable("actionName") String actionName,
                                           @RequestBody Action newAction) throws InterruptedException {
        Optional<Matter> maybeMatter = matterRepository.findById(id);
        if (maybeMatter.isEmpty()) {
            return new ResponseEntity<>(("Not Found"), HttpStatus.NOT_FOUND);
        }
        newAction.setMatter(maybeMatter.get());
        Action action = actionRepository.save(newAction);
        action.setActionName(actionName);
        Instant res = action.startTimer();
        action.setStart(res);
        this.id = action.getId();
        actionRepository.save(action);
        return new ResponseEntity<>("Timer Started", HttpStatus.OK);
    }


    @PostMapping("/stop")
    public ResponseEntity<?> newStopTimer() throws InterruptedException {
        if( this.id == null){
            return new ResponseEntity<>("No active id", HttpStatus.NOT_FOUND);
        }
        Instant res = actionRepository.getReferenceById(id).stopTimer();
        formatTime(res);
        actionRepository.getReferenceById(id).setEnd(res);
        Long duration = actionRepository.getReferenceById(id).displayCurrentTime();
        System.out.println("Duration: " + duration);
        actionRepository.getReferenceById(id).setTime(duration);
        actionRepository.save(actionRepository.getReferenceById(id));
        this.id= null;
        return new ResponseEntity<>("Timer Stopped", HttpStatus.OK);
    }

    public String formatTime(Instant res) {
        DateTimeFormatter formatter = DateTimeFormatter.RFC_1123_DATE_TIME
                .withZone(ZoneId.systemDefault());
        String formattedInstant = formatter.format(res);
        System.out.println(formattedInstant);
        return formattedInstant;
    }
}





    //use action id to add time (timer method = 3 seconds) to a current action
    //maybe a pause later
//    @PostMapping("/actionID/{actionID}")
//    public ResponseEntity<?> addTimeToAction(@PathVariable("actionID") Long id) throws InterruptedException {
//        Optional<Action> existingAction = actionRepository.findById(id);
//        System.out.println(existingAction);
//        if (existingAction.isEmpty()) {
//            return new ResponseEntity<>(("Not Found"), HttpStatus.NOT_FOUND);
//        }
//        Long dataBaseTime = existingAction.get().getTime();
//        System.out.println("dataBaseTime: " + dataBaseTime);
//        Long newTime = (startTimer1() + dataBaseTime); //starting/sopping timer adding to time in database
//        System.out.println(newTime);
//        existingAction.get().setTime(newTime);
//        actionRepository.save(existingAction.get());
//        return new ResponseEntity<>(newTime, HttpStatus.CREATED);
//    }
//
//    public Long startTimer1() throws InterruptedException {
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//        System.out.println("Timer Started");
//        TimeUnit.SECONDS.sleep(3);
//        stopWatch.stop();
//        System.out.println("Timer Ended");
//        Long timer = (long) stopWatch.getTotalTimeSeconds();
//        System.out.println(stopWatch.getTotalTimeSeconds());
//        return timer;
//    }

