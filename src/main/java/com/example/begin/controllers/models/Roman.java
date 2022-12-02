package com.example.begin.controllers.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
public class Roman {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long UID;

    @NotBlank(message = "Строка не должна быть пустой")
    @Size(min=3, max=30, message = "Строка должна быть не больше 30 символов и не меньше 3")
    private String name;

    //@NotEmpty(message = "Строка не должна быть пустой")
    @NotNull(message = "Строка не должна быть пустой")
    private Date dateOfBirth;

    @PositiveOrZero(message = "Значение не может быть меньше нуля")
    @Max(value = 9999999, message = "Введено слишком большое значение")
    @NotNull(message = "Строка не должна быть пустой")
    private Integer netWorth;

    @Size(min=5, max=100)
    @NotBlank(message = "Строка не должна быть пустой")
    private String majorDeeds;

    @Size(min=5, max=100)
    @NotBlank(message = "Строка не должна быть пустой")
    private String ethnicity;

    public Roman(){

    }

    public  Roman(String name, Date dateOfBirth, Integer netWorth, String majorDeeds, String ethnicity){
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.ethnicity = ethnicity;
        this.netWorth = netWorth;
        this.majorDeeds = majorDeeds;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getNetWorth() {
        return netWorth;
    }

    public void setNetWorth(Integer netWorth) {
        this.netWorth = netWorth;
    }

    public String getMajorDeeds() {
        return majorDeeds;
    }

    public void setMajorDeeds(String majorDeeds) {
        this.majorDeeds = majorDeeds;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String profession) {
        this.ethnicity = profession;
    }
}
