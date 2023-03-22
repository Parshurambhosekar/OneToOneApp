package com.parshuram.OneToOneMapping.service;

import com.parshuram.OneToOneMapping.entity.Passport;

import java.util.List;

public interface PassportService {

    //create passport with personId
    Passport createPassport(Passport passport,Integer personId);

    //update passport
    Passport updatePassport(Passport passport,String passportNumber);

    //delete passport
    void deletePassport(String passportNumber);

    //find all passports
    List<Passport> getAllPassportDetails();

    //find passport by Number
    Passport getPassportByNumber(String passportNumber);

    //find by Nationality...
    List<Passport> getPassportByNationality(String nationality);

    //find by state
    List<Passport> getPassportByState(String state);


}
