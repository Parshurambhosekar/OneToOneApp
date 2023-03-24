package com.parshuram.OneToOneMapping;

import com.parshuram.OneToOneMapping.controller.PassportController;
import com.parshuram.OneToOneMapping.entity.ApiResponse;
import com.parshuram.OneToOneMapping.entity.Passport;
import com.parshuram.OneToOneMapping.entity.Person;
import com.parshuram.OneToOneMapping.service.PassportService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PassportControllerMockTest {

    @Mock
    private PassportService passportService;

    @InjectMocks
    private PassportController passportController;


    @Test
    public void test_createPassport(){

        Integer id=1;

        Passport passport=new Passport("bbc11","Indain","Maharashtra", LocalDate.now(),LocalDate.now().plusYears(10),new Person());

        when(passportService.createPassport(passport,id)).thenReturn(passport);

        ResponseEntity<Passport> response = passportController.createPassport(passport, id);

        assertEquals(HttpStatus.CREATED,response.getStatusCode());

        assertEquals(passport,response.getBody());

    }

    @Test
    public void test_updatePassport(){

        String passportNumber="bbc11";

        Passport passport=new Passport("bbc11","Indian","Maharashtra", LocalDate.now(),LocalDate.now().plusYears(10),new Person());

        when(passportService.updatePassport(passport,passportNumber)).thenReturn(passport);

        ResponseEntity<Passport> response = passportController.updatePassport(passport, passportNumber);

        assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());

        assertEquals(passportNumber,response.getBody().getPassportNumber());

        assertEquals("Maharashtra",response.getBody().getState());

        assertEquals("Indian",response.getBody().getNationality());

        assertEquals(LocalDate.now(),response.getBody().getIssuedDate());

        assertEquals(LocalDate.now().plusYears(10),response.getBody().getExpiryDate());

    }

    @Test
    public void test_deletePassport(){

        String passportNumber="bbc11";

        Passport passport=new Passport("bbc11","Indian","Maharashtra", LocalDate.now(),LocalDate.now().plusYears(10),new Person());

        passportService.deletePassport(passportNumber);

        ResponseEntity<ApiResponse> response = passportController.deletePassport(passportNumber);

        assertEquals(HttpStatus.OK,response.getStatusCode());

        assertEquals("Deleted Successfully..........",response.getBody().getMessage());

        assertEquals(true,response.getBody().getSuccess());
    }

    @Test
    public void test_getAllPassportDetails(){

        List<Passport> passportList=new ArrayList<Passport>();
        passportList.add(new Passport("bbc11","Indian","Maharashtra", LocalDate.now(),LocalDate.now().plusYears(10),new Person()));
        passportList.add(new Passport("abc1","American","Sydney", LocalDate.now(),LocalDate.now().plusYears(10),new Person()));
        passportList.add(new Passport("ghj3","Indian","Maharashtra", LocalDate.now(),LocalDate.now().plusYears(10),new Person()));

        when(passportService.getAllPassportDetails()).thenReturn(passportList);

        ResponseEntity<List<Passport>> response = passportController.getAllPassportDetails();

        assertEquals(3,response.getBody().size());

        assertEquals(HttpStatus.OK,response.getStatusCode());

    }

    @Test
    public void test_findByNumber(){

        String passportNumber="bbc11";

        Passport passport=new Passport("bbc11","Indian","Maharashtra", LocalDate.now(),LocalDate.now().plusYears(10),new Person());

        when(passportService.getPassportByNumber(passportNumber)).thenReturn(passport);

        ResponseEntity<Passport> response = passportController.findByNumber(passportNumber);

        assertEquals(HttpStatus.OK,response.getStatusCode());

        assertEquals(passportNumber,response.getBody().getPassportNumber());
    }

    @Test
    public void test_findNationality(){

        String natinality="American";

        List<Passport> passportList=new ArrayList<Passport>();
        passportList.add(new Passport("bbc11","Indian","Maharashtra", LocalDate.now(),LocalDate.now().plusYears(10),new Person()));
        passportList.add(new Passport("abc1","American","Sydney", LocalDate.now(),LocalDate.now().plusYears(10),new Person()));
        passportList.add(new Passport("ghj3","Indian","Maharashtra", LocalDate.now(),LocalDate.now().plusYears(10),new Person()));

        when(passportService.getPassportByNationality(natinality)).thenReturn(passportList);

        ResponseEntity<List<Passport>> response = passportController.findByNationality(natinality);

        List<Passport> list = response.getBody().stream().filter(p -> p.getNationality().equalsIgnoreCase(natinality)).collect(Collectors.toList());

        assertEquals(1,list.size());

        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void test_getState(){

        String state="Maharashtra";

        List<Passport> passportList=new ArrayList<Passport>();
        passportList.add(new Passport("bbc11","Indian","Maharashtra", LocalDate.now(),LocalDate.now().plusYears(10),new Person()));
        passportList.add(new Passport("abc1","American","Sydney", LocalDate.now(),LocalDate.now().plusYears(10),new Person()));
        passportList.add(new Passport("ghj3","Indian","Maharashtra", LocalDate.now(),LocalDate.now().plusYears(10),new Person()));

        when(passportService.getPassportByState(state)).thenReturn(passportList);

        ResponseEntity<List<Passport>> response = passportController.findByState(state);

        List<Passport> list = response.getBody().stream().filter(p -> p.getState().equalsIgnoreCase(state)).collect(Collectors.toList());

        assertEquals(2,list.size());

        assertEquals(HttpStatus.OK,response.getStatusCode());
    }





}
