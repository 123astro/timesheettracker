package com.timesheettracker.timesheettracker.models;

import javax.persistence.*;
import java.time.Duration;
import java.time.Instant;


@Entity
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String actionName;
    public Long time = 0L;
    public Instant start;
    public Instant end;

    @ManyToOne
    @JoinColumn(name = "matter_id", referencedColumnName = "id")
    private Matter matter;

    public Action() {
    }

    public Action(Long id, String actionName, Long time, Instant start, Instant end, Matter matter) {
        this.id = id;
        this.actionName = actionName;
        this.time = time;
        this.start = start;
        this.end = end;
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

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Instant getStart() {
        return start;
    }

    public void setStart(Instant start) {
        this.start = start;
    }

    public Instant getEnd() {
        return end;
    }

    public void setEnd(Instant end) {
        this.end = end;
    }

    public Matter getMatter() {
        return matter;
    }

    public void setMatter(Matter matter) {
        this.matter = matter;
    }


    public Instant startTimer() throws InterruptedException {
        this.start = Instant.now();
        System.out.println(start);
        return start;
    }

    public Instant stopTimer() throws InterruptedException {
        this.end = Instant.now();
        System.out.println(end);
        return end;


    }

    public long displayCurrentTime() {
        Duration timeElapsed = Duration.between(start, end);
        System.out.println(timeElapsed.getSeconds()/60);
        long timeInMin = timeElapsed.getSeconds()/60;
        if ( timeInMin > 0 && timeInMin < 6) {
            System.out.println("Multiplier : .1");
            System.out.println("Number of minutes billed : " + 60 * .1);
        }
        System.out.println("Time taken: " + timeElapsed.getSeconds() + " seconds");
        return timeInMin;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"actionName\":\"" + actionName + '"' +
                ", \"time\":" + time +
                ", \"matter\":" + matter +
                '}';
    }



}
