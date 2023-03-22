package com.parshuram.OneToOneMapping.controller;

import com.parshuram.OneToOneMapping.entity.ApiResponse;
import com.parshuram.OneToOneMapping.entity.Passport;
import com.parshuram.OneToOneMapping.service.PassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/passport")
public class PassportController {

    @Autowired
    private PassportService passportService;

    @PostMapping("/save/{id}")
    public ResponseEntity<Passport> createPassport(@Valid @RequestBody Passport passport, @PathVariable(name = "id") Integer personId){

        Passport passport1 = passportService.createPassport(passport, personId);

        return new ResponseEntity<Passport>(passport1, HttpStatus.CREATED);//ok
    }

    @PutMapping("/{number}")
    public ResponseEntity<Passport> updatePassport(@Valid @RequestBody Passport passport,@PathVariable(name = "number") String passportNumber){

        Passport passport1 = passportService.updatePassport(passport, passportNumber);

        return new ResponseEntity<Passport>(passport1,HttpStatus.ACCEPTED);//ok

    }

    @DeleteMapping("/{number}")
    public ResponseEntity<ApiResponse> deletePassport(@PathVariable(name = "number") String passportNumber){

        passportService.deletePassport(passportNumber);

        return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted Successfully.........",true),HttpStatus.OK);
    }//ok

    @GetMapping("/")
    public ResponseEntity<List<Passport>> getAllPassportDetails(){

        List<Passport> allPassportDetails = passportService.getAllPassportDetails();

        return new ResponseEntity<List<Passport>>(allPassportDetails,HttpStatus.OK);//ok
    }

    @GetMapping("/number/{passportNumber}")
    public ResponseEntity<Passport> findByNumber(@PathVariable String passportNumber){

        Passport passportByNumber = passportService.getPassportByNumber(passportNumber);

        return new ResponseEntity<Passport>(passportByNumber,HttpStatus.OK);//ok
    }

    @GetMapping("/nationality/{lity}")
    public ResponseEntity<List<Passport>> findByNationality(@PathVariable(name = "lity") String nationality){

        List<Passport> passportByNationality = passportService.getPassportByNationality(nationality);

        return ResponseEntity.ok().body(passportByNationality);//ok
    }

    @GetMapping("/state/{state}")
    public ResponseEntity<List<Passport>> findByState(@PathVariable String state){

        List<Passport> passportByState = passportService.getPassportByState(state);

        return ResponseEntity.ok().body(passportByState);//ok
    }




}
