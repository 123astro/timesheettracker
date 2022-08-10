package com.timesheettracker.timesheettracker.models;
import org.springframework.util.StopWatch;

import javax.persistence.*;
import java.util.concurrent.TimeUnit;

@Entity
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String actionName;
    public Long time = 0L;

    @ManyToOne
    @JoinColumn(name = "matter_id", referencedColumnName = "id")
    private Matter matter;

    public Action() {
    }

    public Action(Long id, String actionName, Long time, Matter matter) {
        this.id = id;
        this.actionName = actionName;
        this.time = time;
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

    public Matter getMatter() {
        return matter;
    }

    public void setMatter(Matter matter) {
        this.matter = matter;
    }

    public Long startTimer() throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println("Timer Started");
        TimeUnit.SECONDS.sleep(3);
        stopWatch.stop();
        System.out.println("Timer Ended");
        Long timer = (long) stopWatch.getTotalTimeSeconds();
        setTime(timer);
        System.out.println(stopWatch.getTotalTimeSeconds());
        return timer;
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
