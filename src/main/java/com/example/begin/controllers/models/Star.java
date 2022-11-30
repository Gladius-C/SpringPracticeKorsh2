package com.example.begin.controllers.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Star {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long UID;

    @Size(min=2, max=50, message = "Неверное значение")
    @NotBlank(message = "Строка не должна быть пустой")
    private String name;

    @Size(min =2 , max = 57, message = "Неверное значение")
    @NotNull()
    private String classStar;

    @Min(1)
    @Max(999999)
    private Integer massStar;

    public Star(){

    }

    public Star(String name, String classStar, Integer massStar) {
        this.name = name;
        this.classStar = classStar;
        this.massStar = massStar;
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

    public String getClassStar() {
        return classStar;
    }

    public void setClassStar(String classStar) {
        this.classStar = classStar;
    }

    public Integer getMassStar() {
        return massStar;
    }

    public void setMassStar(Integer massStar) {
        this.massStar = massStar;
    }
}
