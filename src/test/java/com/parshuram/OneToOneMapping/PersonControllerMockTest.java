package com.parshuram.OneToOneMapping;

import com.parshuram.OneToOneMapping.controller.PersonController;
import com.parshuram.OneToOneMapping.entity.ApiResponse;
import com.parshuram.OneToOneMapping.entity.Person;
import com.parshuram.OneToOneMapping.service.PersonService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PersonControllerMockTest {

    @Mock
    private PersonService personService;

    @InjectMocks
    private PersonController personController;

    @Test
    public void test_createPerson(){

        Person person=new Person(1,"Parshuram","Sangli","Male",9623771726l,"p@gamil.com");

        when(personService.createPerson(person)).thenReturn(person);

        ResponseEntity<Person> response = personController.createPerson(person);

        assertEquals(HttpStatus.CREATED,response.getStatusCode());

        assertEquals(person,response.getBody());
    }

    @Test
    public void test_updatePerson(){

        Integer id=1;

        Person person=new Person(1,"Parshuram","Sangli","Male",9623771726l,"p@gmail.com");

        when(personService.updatePerson(person,id)).thenReturn(person);

        ResponseEntity<Person> response = personController.updatePerson(person, id);

        assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());

        assertEquals(id,response.getBody().getId());

        assertEquals("Sangli",response.getBody().getCity());

        assertEquals("Parshuram",response.getBody().getName());

        assertEquals("Male",response.getBody().getGender());

        assertEquals("p@gmail.com",response.getBody().getEmail());

        assertEquals(9623771726l,response.getBody().getMobileNumber());

    }

    @Test
    public void test_getAllPerson(){

        List<Person> personList=new ArrayList<Person>();
        personList.add(new Person(1,"Parshuram","Sangli","Male",9623771726l,"p@gmail.com"));
        personList.add(new Person(2,"Suchitra","Sangli","Fe-Male",1245771726l,"s@gmail.com"));
        personList.add(new Person(3,"Darshan","Pune","Male",9679771726l,"d@gmail.com"));

        when(personService.getAllPerson()).thenReturn(personList);

        ResponseEntity<List<Person>> response = personController.getAllPerson();

        assertEquals(HttpStatus.OK,response.getStatusCode());

        assertEquals(3,response.getBody().size());
    }

    @Test
    public void test_deletePerson(){

        Integer id=1;

        Person person=new Person(1,"Parshuram","Sangli","Male",9623771726l,"p@gmail.com");

        personService.deletePerson(id);

        ResponseEntity<ApiResponse> response = personController.deletePerSon(id);

        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void test_findByName(){

        String name="Parshuram";

        Person person=new Person(1,"Parshuram","Sangli","Male",9623771726l,"p@gmail.com");

        when(personService.getPersonByName(name)).thenReturn(person);

        ResponseEntity<Person> response = personController.findByName(name);

        assertEquals(HttpStatus.OK,response.getStatusCode());

        assertEquals(name,response.getBody().getName());
    }

    @Test
    public void test_findByCity(){

        String  city="Pune";

        List<Person> personList=new ArrayList<Person>();
        personList.add(new Person(1,"Parshuram","Sangli","Male",9623771726l,"p@gmail.com"));
        personList.add(new Person(2,"Suchitra","Sangli","Fe-Male",1245771726l,"s@gmail.com"));
        personList.add(new Person(3,"Darshan","Pune","Male",9679771726l,"d@gmail.com"));

        when(personService.getPersonByCity(city)).thenReturn(personList);//ok

        ResponseEntity<List<Person>> response = personController.findByCity(city);

        List<Person> list = response.getBody().stream().filter(x -> x.getCity().equalsIgnoreCase(city)).collect(Collectors.toList());

        assertEquals(1,list.size());

        assertEquals(HttpStatus.OK,response.getStatusCode());

    }

    @Test
    public void test_getPersonByNameAndCity(){

        String name="Parshuram";

        String city="Sangli";

        Person person=new Person(1,"Parshuram","Sangli","Male",9623771726l,"p@gmail.com");

        when(personService.getPersonByNameAndCity(name,city)).thenReturn(person);

        ResponseEntity<Person> response = personController.findByNameAndCity(name, city);

        assertEquals(HttpStatus.OK,response.getStatusCode());

        assertEquals(name,response.getBody().getName());

        assertEquals(city,response.getBody().getCity());
    }

    @Test
    public void test_findPersonById(){

        Integer id=1;

        Person person=new Person(1,"Parshuram","Sangli","Male",9623771726l,"p@gmail.com");

        when(personService.getPersonById(id)).thenReturn(person);

        ResponseEntity<Person> response = personController.findById(id);

        assertEquals(id,response.getBody().getId());

        assertEquals(HttpStatus.OK,response.getStatusCode());

    }



}
