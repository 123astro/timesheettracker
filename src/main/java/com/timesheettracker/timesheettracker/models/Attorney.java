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
    private Float billingRate;

    @OneToMany(mappedBy = "attorney", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Client> clients;

    public Attorney() {
    }

    public Attorney(Long id, String name, Float billingRate) {
        this.id = id;
        this.name = name;
        this.billingRate = billingRate;
    }

    public Float getBillingRate() {
        return billingRate;
    }

    public void setBillingRate(Float billingRate) {
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
