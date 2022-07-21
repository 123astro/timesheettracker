package com.timesheettracker.timesheettracker.models;
import org.springframework.util.StopWatch;

import javax.persistence.*;

@Entity
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String actionName;
    private long time;

    @ManyToOne
    @JoinColumn(name = "matter_id", referencedColumnName = "id")
    private Client matter;

    public Action() {
    }

    public Action(Long id, String actionName, long time, Client matter) {
        this.id = id;
        this.actionName = actionName;
        this.time = 0;
        this.matter = matter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public Client getMatter() {
        return matter;
    }

    public void setMatter(Client matter) {
        this.matter = matter;
    }


    public long getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void startTimer(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println("Timer");
        System.out.println("Timer");
        System.out.println("Timer");
        stopWatch.stop();
        setTime((int) stopWatch.getTotalTimeNanos());
        System.out.println(stopWatch.getTotalTimeNanos());
    }

//    public void stopTimer(){
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.stop();
//        System.out.println(stopWatch.getTotalTimeMillis());
//    }

}
