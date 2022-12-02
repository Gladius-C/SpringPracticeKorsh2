package com.example.begin.controllers.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import javax.xml.crypto.Data;
import java.util.Date;

@Entity
public class Battle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long UID;

    @NotBlank(message = "Строка не должна быть пустой")
    @Size(min=3, max=30, message = "Строка должна быть не больше 30 символов и не меньше 3")
    private String location;

    @NotBlank(message = "Строка не должна быть пустой")
    private Date date;

    @NotBlank(message = "Строка не должна быть пустой")
    private String defender;

    @NotBlank(message = "Строка не должна быть пустой")
    private String attacker;

    @Min(value = 0, message = "Значение не может быть меньше нуля")
    @Max(value = 9999999, message = "Введено слишком большое значение")
    @NotNull(message = "Строка не должна быть пустой")
    private Integer casualties;

    @NotBlank(message = "Строка не должна быть пустой")
    private String winner;

    public Battle(){

    }

    public Battle(String location, Date date, String defender,String attacker,Integer casualties,String winner){
        this.location = location;
        this.date = date;
        this.defender = defender;
        this.attacker = attacker;
        this.casualties = casualties;
        this.winner = winner;
    }

    public Long getUID() {
        return UID;
    }

    public void setUID(Long UID) {
        this.UID = UID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDefender() {
        return defender;
    }

    public void setDefender(String defender) {
        this.defender = defender;
    }

    public String getAttacker() {
        return attacker;
    }

    public void setAttacker(String attacker) {
        this.attacker = attacker;
    }

    public Integer getCasualties() {
        return casualties;
    }

    public void setCasualties(Integer casualties) {
        this.casualties = casualties;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
