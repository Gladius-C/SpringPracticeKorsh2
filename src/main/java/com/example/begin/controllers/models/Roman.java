package com.example.begin.controllers.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;
import java.util.Collection;

import java.util.List;

@Entity
@Table(name = "roman")
public class Roman {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long UID;

    @NotBlank(message = "Строка не должна быть пустой")
    @Size(max=30, message = "Строка должна быть не больше 30 символов и не меньше 3")
    private String name;

    @NotNull(message = "Строка не должна быть пустой")

    private Date dateOfBirth;

    @PositiveOrZero(message = "Значение не может быть меньше нуля")
    @Max(value = 9999999, message = "Введено слишком большое значение")
    @NotNull(message = "Строка не должна быть пустой")
    private Integer netWorth;

    @Size(max=100)
    @NotBlank(message = "Строка не должна быть пустой")
    private String majorDeeds;

    @Size(max=100)
    @NotBlank(message = "Строка не должна быть пустой")
    private String ethnicity;

    //@OneToMany(mappedBy = "roman", fetch = FetchType.EAGER, orphanRemoval = true)
    //public Collection<Army> army;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "army_uid")
    private Army army;

    @ManyToMany
    @JoinTable(name = "battle_roman",
            joinColumns = @JoinColumn(name = "battle_uid"),
            inverseJoinColumns = @JoinColumn(name = "roman_uid"))
    private Collection<Battle> battle;

    public Collection<Battle> getBattle() {
        return battle;
    }

    public void setBattle(Collection<Battle> battle) {
        this.battle = battle;
    }

    public Roman(){

    }

    public  Roman(String name, Date dateOfBirth, Integer netWorth, String majorDeeds, String ethnicity,Army army){
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.ethnicity = ethnicity;
        this.netWorth = netWorth;
        this.majorDeeds = majorDeeds;
        this.army = army;
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

    public Army getArmy() {
        return army;
    }

    public void setArmy(Army army) {
        this.army = army;
    }
}
