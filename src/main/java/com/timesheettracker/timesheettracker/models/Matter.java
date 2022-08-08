package com.timesheettracker.timesheettracker.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import javax.persistence.*;
import java.util.Set;

@Entity

public class Matter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String matterName;


    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
   // I took away @JsonIgnore
    private Client client;


    @ManyToOne
    @JoinColumn(name = "attorney_id", referencedColumnName = "id")
     @JsonIgnore
    private Attorney attorney;

    @OneToMany(mappedBy = "matter", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Action> actions;

    public Matter() {
    }

    public Matter(Long id, String matterName, Client client) {
        this.id = id;
        this.matterName = matterName;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatterName() {
        return matterName;
    }

    public void setMatterName(String matterName) {
        this.matterName = matterName;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Attorney getAttorney() {
        return attorney;
    }

    public void setAttorney(Attorney attorney) {
        this.attorney = attorney;
    }

    public Set<Action> getActions() {
        return actions;
    }

    public void setActions(Set<Action> actions) {
        this.actions = actions;
    }
}
