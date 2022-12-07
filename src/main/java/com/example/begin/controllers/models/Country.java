package com.example.begin.controllers.models;


import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "country")
    public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long UID;

    private String name;

    private int population;

    @OneToMany(mappedBy = "country", fetch = FetchType.EAGER, orphanRemoval = true)
    public Collection<Army> army;
    //@OneToOne(optional = true,cascade = CascadeType.ALL)
    //@JoinColumn(name = "army_uid")
    //private Army army;

    public Country(){

    }

    public Country(String name, int population){
        this.name=name;
        this.population=population;
        this.army=army;
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

    public Collection<Army> getArmy() {
        return army;
    }

    public void setArmy(Collection<Army> army) {
        this.army = army;
    }
}