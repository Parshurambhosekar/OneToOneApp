package com.parshuram.OneToOneMapping.controller;

import com.parshuram.OneToOneMapping.entity.ApiResponse;
import com.parshuram.OneToOneMapping.entity.Person;
import com.parshuram.OneToOneMapping.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/")
    public ResponseEntity<Person> createPerson(@Valid @RequestBody Person person){

        Person person1 = personService.createPerson(person);

        return new ResponseEntity<Person>(person1, HttpStatus.CREATED);//ok
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@Valid @RequestBody Person person,@PathVariable Integer id){

        Person person1 = personService.updatePerson(person, id);

        return new ResponseEntity<Person>(person1,HttpStatus.ACCEPTED);//ok

    }

    @GetMapping("/")
    public ResponseEntity<List<Person>> getAllPerson(){

        List<Person> personList = personService.getAllPerson();

        return ResponseEntity.ok().body(personList);//ok

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deletePerSon(@PathVariable Integer id){

        personService.deletePerson(id);

        return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted Successfully........",true),HttpStatus.OK);
    }//ok

    @GetMapping("/personName/{name}")
    public ResponseEntity<Person> findByName(@PathVariable String name){

        Person personByName = personService.getPersonByName(name);

        return new ResponseEntity<Person>(personByName,HttpStatus.OK);//ok
    }

    @GetMapping("/personCity/{city}")
    public ResponseEntity<List<Person>> findByCity(@PathVariable String city){

        List<Person> personByCity = personService.getPersonByCity(city);

        return new ResponseEntity<List<Person>>(personByCity,HttpStatus.OK);//ok
    }

    @GetMapping("/personNameCity/{name}/{city}")
    public ResponseEntity<Person> findByNameAndCity(@PathVariable String name,@PathVariable String city){

        Person personByNameAndCity = personService.getPersonByNameAndCity(name, city);

        return new ResponseEntity<Person>(personByNameAndCity,HttpStatus.OK);//ok

    }

    @GetMapping("/{personId}")
    public ResponseEntity<Person> findById(@PathVariable(name = "personId") Integer id){

        Person personById = personService.getPersonById(id);

        return new ResponseEntity<Person>(personById,HttpStatus.OK);//ok
    }



}
