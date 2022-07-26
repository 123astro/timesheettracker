package com.timesheettracker.timesheettracker.models;
import com.timesheettracker.timesheettracker.repositories.ActionRepository;
import org.springframework.util.StopWatch;

import javax.persistence.*;
import java.util.concurrent.TimeUnit;

@Entity
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String actionName;
    private Long time = 0L;
    //private static StopWatch stopWatch = new StopWatch();

    @ManyToOne
    @JoinColumn(name = "matter_id", referencedColumnName = "id")
    private Client matter;

    public Action() {
    }

    public Action(Long id, String actionName, Long time, Client matter) {
        this.id = id;
        this.actionName = actionName;
        this.time = time;
        this.matter = matter;
       // this.stopWatch = stopWatch;
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
        //this.time += time;
    }

    public Client getMatter() {
        return matter;
    }

    public void setMatter(Client matter) {
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

    //    public void stopTimer(){
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.stop();
//        System.out.println(stopWatch.getTotalTimeMillis());
//    }

}
