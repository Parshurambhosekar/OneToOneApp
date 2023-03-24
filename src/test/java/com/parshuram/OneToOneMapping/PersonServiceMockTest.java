package com.parshuram.OneToOneMapping;

import com.parshuram.OneToOneMapping.entity.Person;
import com.parshuram.OneToOneMapping.repository.PersonRepository;
import com.parshuram.OneToOneMapping.serviceimpl.PersonServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PersonServiceMockTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonServiceImpl personService;


    @Test
    public void test_createPerson(){

        Person person=new Person(1,"Parshuram","Sangli","Male",9623771726l,"p@gmail.com");

        when(personService.createPerson(person)).thenReturn(person);

        assertEquals(person,personRepository.save(person));
    }

    @Test
    public void test_updatePerson(){

        Integer id=1;

        Person person=new Person(1,"Parshuram","Sangli","Male",9623771726l,"p@gmail.com");

        when(personRepository.findById(id)).thenReturn(Optional.ofNullable(person));

        when(personRepository.save(person)).thenReturn(person);

        assertEquals(id,personService.updatePerson(person,id).getId());

    }

    @Test
    public void test_getAllperson(){

        List<Person> personList=new ArrayList<Person>();
        personList.add(new Person(1,"Parshuram","Sangli","Male",9623771726l,"p@gmail.com"));
        personList.add(new Person(2,"Prashant","Sangli","Male",9623772926l,"par@gmail.com"));
        personList.add(new Person(3,"Pratha","Pune","Fef-Male",9623772726l,"prtha@gmail.com"));

        when(personRepository.findAll()).thenReturn(personList);

        assertEquals(3,personService.getAllPerson().size());
    }

    @Test
    public void test_deletePerson(){

        Integer id=1;

        Person person=new Person(1,"Parshuram","Sangli","Male",9623771726l,"p@gmail.com");

        when(personRepository.existsById(id)).thenReturn(true);

        personService.deletePerson(id);

        verify(personRepository,times(1)).deleteById(id);

    }

    @Test
    public void test_getPersonByName(){

        String name="Parshuram";

        Person person=new Person(1,"Parshuram","Sangli","Male",9623771726l,"p@gmail.com");

        when(personRepository.findByName(name)).thenReturn(person);

        assertEquals(name,personService.getPersonByName(name).getName());

    }

    @Test
    public void test_getPersonByCity(){

        String city="Pune";

        List<Person> personList=new ArrayList<Person>();
        personList.add(new Person(1,"Parshuram","Sangli","Male",9623771726l,"p@gmail.com"));
        personList.add(new Person(2,"Prashant","Sangli","Male",9623772926l,"par@gmail.com"));
        personList.add(new Person(3,"Pratha","Pune","Fef-Male",9623772726l,"prtha@gmail.com"));

        when(personRepository.findAll()).thenReturn(personList);

        List<Person> list = personList.stream().filter(p -> p.getCity().equalsIgnoreCase(city)).collect(Collectors.toList());

        assertEquals(list,personService.getPersonByCity(city));

    }

    @Test
    public void test_getPersonByNameAndCity(){

        String name="Parshuram";

        String city="Sangli";

        Person person=new Person(1,"Parshuram","Sangli","Male",9623771726l,"p@gmail.com");

        when(personRepository.findByNameAndCity(name,city)).thenReturn(person);

        assertEquals(name,personService.getPersonByNameAndCity(name,city).getName());

        assertEquals(city,personService.getPersonByNameAndCity(name,city).getCity());
    }

    @Test
    public void test_getPersonById(){

        Integer id=3;

        List<Person> personList=new ArrayList<Person>();
        personList.add(new Person(1,"Parshuram","Sangli","Male",9623771726l,"p@gmail.com"));
        personList.add(new Person(2,"Prashant","Sangli","Male",9623772926l,"par@gmail.com"));
        personList.add(new Person(3,"Pratha","Pune","Fef-Male",9623772726l,"prtha@gmail.com"));


        when(personRepository.findAll()).thenReturn(personList);

        assertEquals(3,personService.getPersonById(id).getId());

    }


}
