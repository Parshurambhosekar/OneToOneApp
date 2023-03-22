package com.parshuram.OneToOneMapping.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
@Table(name = "passport_details")
public class Passport {

    @Id
    private String passportNumber;
    @NotEmpty(message = "nationality is not Empty....")
    private String nationality;
    @NotEmpty(message = "State must be provided........")
    private String state;
    private LocalDate issuedDate;
    private LocalDate expiryDate;

    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalDate getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(LocalDate issuedDate) {
        this.issuedDate = issuedDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Passport(String passportNumber, String nationality, String state, LocalDate issuedDate, LocalDate expiryDate, Person person) {
        this.passportNumber = passportNumber;
        this.nationality = nationality;
        this.state = state;
        this.issuedDate = issuedDate;
        this.expiryDate = expiryDate;
        this.person = person;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "passportNumber='" + passportNumber + '\'' +
                ", nationality='" + nationality + '\'' +
                ", state='" + state + '\'' +
                ", issuedDate=" + issuedDate +
                ", expiryDate=" + expiryDate +
                ", person=" + person +
                '}';
    }

    public Passport(){


    }
}
