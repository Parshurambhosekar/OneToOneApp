package com.parshuram.OneToOneMapping.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "person_details")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Integer id;
    @Column(name = "person_name")
    @NotEmpty(message = "name is not Empty")
    private String name;
    @Column(name = "person_city")
    @NotEmpty(message = "city is not Empty")
    private String city;
    @NotEmpty(message = "Gender is not Empty.")
    private String gender;
    @NotNull
    @Range(min = 10,max = 10,message = "Phone Number Should exact on 10 digits........")//range
    private Long mobileNumber;
    @Email(message = "email is not Valid..........")
    private String email;

    @OneToOne(mappedBy = "person",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    private Passport passport;

    public Person(Integer id, String name, String city, String gender, Long mobileNumber, String email, Passport passport) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.passport = passport;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", gender='" + gender + '\'' +
                ", mobileNumber=" + mobileNumber +
                ", email='" + email + '\'' +
                ", passport=" + passport +
                '}';
    }

    public Person(){

    }
}
