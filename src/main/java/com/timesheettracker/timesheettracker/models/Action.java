package com.timesheettracker.timesheettracker.models;


import javax.persistence.*;

@Entity
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String actionName;

    @ManyToOne
    @JoinColumn(name = "matter_id", referencedColumnName = "id")
    private Client matter;
}
