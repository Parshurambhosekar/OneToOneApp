package com.parshuram.OneToOneMapping.serviceimpl;

import com.parshuram.OneToOneMapping.entity.Passport;
import com.parshuram.OneToOneMapping.entity.Person;
import com.parshuram.OneToOneMapping.exception.ResourceNotFoundException;
import com.parshuram.OneToOneMapping.repository.PassportRepository;
import com.parshuram.OneToOneMapping.repository.PersonRepository;
import com.parshuram.OneToOneMapping.service.PassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PassportServiceImpl implements PassportService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PassportRepository  passportRepository;


    @Override
    public Passport createPassport(Passport passport, Integer personId) {

        String passportNumber = UUID.randomUUID().toString();

        Person person = personRepository.findById(personId).orElseThrow(() -> new ResourceNotFoundException("Person", "personId", personId));

        LocalDate expiry = LocalDate.now().plusYears(10);

        passport.setPassportNumber(passportNumber);
        passport.setPerson(person);
        passport.setExpiryDate(expiry);

        Passport createPassport = passportRepository.save(passport);

        return createPassport;
    }

    @Override
    public Passport updatePassport(Passport passport, String passportNumber) {


        Passport passport1 = passportRepository.findById(passportNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Passport", "PassportNumber", passportNumber));

        passport1.setPassportNumber(passport.getPassportNumber());
        passport1.setState(passport.getState());
        passport1.setNationality(passport.getNationality());
        passport1.setIssuedDate(passport.getIssuedDate());
        passport1.setExpiryDate(passport.getExpiryDate());

        Passport updatePassport = passportRepository.save(passport1);

        return updatePassport;
    }

    @Override
    public void deletePassport(String passportNumber) {

        Passport passport = passportRepository.findById(passportNumber)
                .orElseThrow(() -> new ResourceNotFoundException("passport", "PassportNumber", passportNumber));

        passportRepository.delete(passport);
    }

    @Override
    public List<Passport> getAllPassportDetails() {

        List<Passport> passportList = passportRepository.findAll();

        return passportList;
    }

    @Override
    public Passport getPassportByNumber(String passportNumber) {

        Passport passport = passportRepository.findById(passportNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Passport", "PassportNumber", passportNumber));

        return passport;
    }

    @Override
    public List<Passport> getPassportByNationality(String nationality) {

        List<Passport> passports = passportRepository.findByNationality(nationality);

        if (passports.isEmpty()){
            throw new ResourceNotFoundException("Passport","Nationality",nationality);
        }

        return passports;
    }

    @Override
    public List<Passport> getPassportByState(String state) {

        List<Passport> passportList = passportRepository.findAll();

        List<Passport> passportList1 = passportList.stream().filter(p -> p.getState().equalsIgnoreCase(state)).collect(Collectors.toList());

        if (passportList1.isEmpty()){
            throw new ResourceNotFoundException("Passport","State",state);
        }

        return passportList1;
    }
}
