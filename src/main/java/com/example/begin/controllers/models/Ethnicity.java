package com.example.begin.controllers.models;

import javax.persistence.*;

@Entity
@Table(name = "ethnicity")
public class Ethnicity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long UID;

    private String name;

    private int population;

    public Ethnicity(){

    }
    public Ethnicity(String name, int population){
        this.name=name;
        this.population=population;
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

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
