package com.example.begin.controllers.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "ruler")
public class Ruler {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long UID;

    private String name;

    private Date dateOfSign;

    private Date dateOfResign;

    public Ruler(){

    }
    public Ruler(String name, Date dateOfSign, Date dateOfResign){
        this.name=name;
        this.dateOfSign=dateOfSign;
        this.dateOfResign=dateOfResign;
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

    public Date getDateOfSign() {
        return dateOfSign;
    }

    public void setDateOfSign(Date dateOfSign) {
        this.dateOfSign = dateOfSign;
    }

    public Date getDateOfResign() {
        return dateOfResign;
    }

    public void setDateOfResign(Date dateOfResign) {
        this.dateOfResign = dateOfResign;
    }
}
