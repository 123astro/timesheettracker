package com.timesheettracker.timesheettracker.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Attorney {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer billingRate;

    @ManyToMany
    @JsonIgnore
    Set<Client> clients;

    public Attorney() {
    }

    public Attorney(Long id, String name, Integer billingRate) {
        this.id = id;
        this.name = name;
        this.billingRate = billingRate;
    }

    public Integer getBillingRate() {
        return billingRate;
    }

    public void setBillingRate(Integer billingRate) {
        this.billingRate = billingRate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
