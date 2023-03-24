package com.parshuram.OneToOneMapping;

import com.parshuram.OneToOneMapping.entity.Passport;
import com.parshuram.OneToOneMapping.entity.Person;
import com.parshuram.OneToOneMapping.repository.PassportRepository;
import com.parshuram.OneToOneMapping.repository.PersonRepository;
import com.parshuram.OneToOneMapping.serviceimpl.PassportServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PassportServiceMockTest {

    @Mock
    private PassportRepository passportRepository;

    @Mock
    private PersonRepository  personRepository;

    @InjectMocks
    private PassportServiceImpl passportService;


    @Test
    public void test_createPassport(){

        Integer personId=1;

        Passport passport=new Passport("bc11","Indian","Maharashtra", LocalDate.now(),LocalDate.now().plusYears(10),new Person());

        when(personRepository.findById(personId)).thenReturn(Optional.ofNullable(new Person()));

        when(passportRepository.save(passport)).thenReturn(passport);

        assertEquals(passport,passportService.createPassport(passport,personId));

    }

    @Test
    public void test_updatePassport(){

        String passportNumber="bc11";

        Passport passport=new Passport("bc11","Indian","Maharashtra", LocalDate.now(),LocalDate.now().plusYears(10),new Person());

        when(passportRepository.findById(passportNumber)).thenReturn(Optional.ofNullable(passport));

        when(passportRepository.save(passport)).thenReturn(passport);

        assertEquals(passportNumber,passportService.updatePassport(passport,passportNumber).getPassportNumber());
    }

    @Test
    public void test_deletePassport(){

        String passportNumber="bc11";

        Passport passport=new Passport("bc11","Indian","Maharashtra", LocalDate.now(),LocalDate.now().plusYears(10),new Person());

        when(passportRepository.findById(passportNumber)).thenReturn(Optional.ofNullable(passport));

        passportService.deletePassport(passportNumber);

        verify(passportRepository,times(1)).delete(passport);
    }

    @Test
    public void test_getAllPassportDetails(){

        List<Passport> passportList=new ArrayList<Passport>();
        passportList.add(new Passport("bc11","Indian","Maharashtra", LocalDate.now(),LocalDate.now().plusYears(10),new Person()));
        passportList.add(new Passport("aa12","Indian","Maharashtra", LocalDate.now(),LocalDate.now().plusYears(10),new Person()));
        passportList.add(new Passport("cc01","Indian","Telangana", LocalDate.now(),LocalDate.now().plusYears(10),new Person()));

        when(passportRepository.findAll()).thenReturn(passportList);

        assertEquals(3,passportService.getAllPassportDetails().size());
    }

    @Test
    public void test_getPassportByNumber(){

        String passportNumber="bc11";

        Passport passport=new Passport("bc11","Indian","Maharashtra", LocalDate.now(),LocalDate.now().plusYears(10),new Person());

        when(passportRepository.findById(passportNumber)).thenReturn(Optional.ofNullable(passport));

        assertEquals(passportNumber,passportService.getPassportByNumber(passportNumber).getPassportNumber());
    }

    @Test
   public void test_getPassportByState(){

        String state="Telangana";

        List<Passport> passportList=new ArrayList<Passport>();
        passportList.add(new Passport("bc11","Indian","Maharashtra", LocalDate.now(),LocalDate.now().plusYears(10),new Person()));
        passportList.add(new Passport("aa12","Indian","Maharashtra", LocalDate.now(),LocalDate.now().plusYears(10),new Person()));
        passportList.add(new Passport("cc01","Indian","Telangana", LocalDate.now(),LocalDate.now().plusYears(10),new Person()));

        when(passportRepository.findAll()).thenReturn(passportList);

        List<Passport> passports = passportList.stream().filter(p -> p.getState().equalsIgnoreCase(state)).collect(Collectors.toList());

        assertEquals(passports,passportService.getPassportByState(state));
    }

    @Test
    public void test_getPassportByNationality(){

        String nationality="American";

        List<Passport> passportList=new ArrayList<Passport>();
        passportList.add(new Passport("bc11","Indian","Maharashtra", LocalDate.now(),LocalDate.now().plusYears(10),new Person()));
        passportList.add(new Passport("aa12","Indian","Maharashtra", LocalDate.now(),LocalDate.now().plusYears(10),new Person()));
        passportList.add(new Passport("cc01","American","Sydney", LocalDate.now(),LocalDate.now().plusYears(10),new Person()));

        when(passportRepository.findByNationality(nationality)).thenReturn(passportList);//mocking..

        List<Passport> passportByNationality = passportService.getPassportByNationality(nationality);

        List<Passport> list = passportByNationality.stream().filter(p -> p.getNationality().equalsIgnoreCase(nationality)).collect(Collectors.toList());

        assertEquals(1,list.size());

    }


}
