package com.timesheettracker.timesheettracker.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.Set;

@Entity

public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private String contactName;
    private String phoneNumber;

    //Many clients to one attorney
    @ManyToOne
    @JoinColumn(name = "attorney_id", referencedColumnName = "id")
    @JsonIgnore
    private Attorney attorney;

    //one client to many matters
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Matter> matters;


    public Client() {
    }

    public Client(Long id, String companyName, String contactName, String phoneNumber, Attorney attorney, Set<Matter> matters) {
        this.id = id;
        this.companyName = companyName;
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
        this.attorney = attorney;
        this.matters = matters;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Attorney getAttorney() {
        return attorney;
    }

    public void setAttorney(Attorney attorney) {
        this.attorney = attorney;
    }

    public Set<Matter> getMatters() {
        return matters;
    }

    public void setMatters(Set<Matter> matters) {
        this.matters = matters;
    }
}
