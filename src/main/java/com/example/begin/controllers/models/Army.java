package com.example.begin.controllers.models;

import javax.persistence.*;
import java.rmi.server.UID;

@Entity
@Table(name = "armies")
public class Army {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long UID;

    private String name;

    private int power;

    @OneToOne(optional = true, mappedBy = "army")
    private Roman roman;
    @ManyToOne(optional = true,cascade = CascadeType.ALL)
    private Country country;

    //@ManyToOne(optional = true,cascade = CascadeType.ALL)
    //private Roman roman;

    public Army(){

    }

    public Army(String name,int power,Roman roman){
        this.name = name;
        this.power = power;
        this.roman=roman;

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

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Roman getRoman() {
        return roman;
    }

    public void setRoman(Roman roman) {
        this.roman = roman;
    }
}
