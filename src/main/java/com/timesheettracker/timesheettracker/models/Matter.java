package com.timesheettracker.timesheettracker.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private Client client;

    @OneToMany(mappedBy = "matter", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Action> Action;

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
}
