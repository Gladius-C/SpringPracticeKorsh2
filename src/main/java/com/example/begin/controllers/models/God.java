package com.example.begin.controllers.models;

import javax.persistence.*;

@Entity
@Table(name = "god")
public class God {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long UID;

    private String name;

    private String speciality;

    private String believers;

    public God(){

    }

    public God(String name,String speciality,String believers){
        this.believers = believers;
        this.name=name;
        this.speciality=speciality;
    }

    public Long getUID() {
        return UID;
    }

    public void setUID(Long UID) {
        this.UID = UID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getBelievers() {
        return believers;
    }

    public void setBelievers(String believers) {
        this.believers = believers;
    }
}
