package com.parshuram.OneToOneMapping.service;

import com.parshuram.OneToOneMapping.entity.Person;

import java.util.List;

public interface PersonService {

    //create person
    Person createPerson(Person person);

    //update Person
    Person updatePerson(Person person,Integer id);

    //get all persons
    List<Person> getAllPerson();

    //delete persons
    void deletePerson(Integer id);

    //get person by name
    Person getPersonByName(String name);

    //find by city
    List<Person> getPersonByCity(String city);

    //find person by name and city
    Person getPersonByNameAndCity(String name,String city);

    //find person by id
    Person getPersonById(Integer id);


}
