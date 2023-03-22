package com.parshuram.OneToOneMapping.serviceimpl;

import com.parshuram.OneToOneMapping.entity.Person;
import com.parshuram.OneToOneMapping.exception.ResourceNotFoundException;
import com.parshuram.OneToOneMapping.repository.PersonRepository;
import com.parshuram.OneToOneMapping.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository)
    {
        this.personRepository=personRepository;

    }

    @Override
    public Person createPerson(Person person) {

        Person savePerson = personRepository.save(person);

        return savePerson;
    }

    @Override
    public Person updatePerson(Person person, Integer id) {

        Person person1 = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person", "PersonId", id));

        person1.setId(person.getId());
        person1.setName(person.getName());
        person1.setCity(person.getCity());
        person1.setGender(person.getGender());
        person1.setMobileNumber(person.getMobileNumber());
        person1.setEmail(person.getEmail());

        Person updatePerson = personRepository.save(person1);

        return updatePerson;
    }

    @Override
    public List<Person> getAllPerson() {

        List<Person> personList = personRepository.findAll();

        return personList;
    }

    @Override
    public void deletePerson(Integer id) {

        if (personRepository.existsById(id)){
            personRepository.deleteById(id);
        }
        else {
            throw new ResourceNotFoundException("person","PersonId",id);
        }
    }

    @Override
    public Person getPersonByName(String name) {

        Person personByName = personRepository.findByName(name);

        if (personByName==null){
            throw new ResourceNotFoundException("Person","PersonName",name);
        }

        return personByName;
    }

    @Override
    public List<Person> getPersonByCity(String city) {

        List<Person> personList = personRepository.findAll();

        List<Person> personList1 = personList.stream().filter(p -> p.getCity().equalsIgnoreCase(city)).collect(Collectors.toList());

        if (personList1.isEmpty()){
            throw new ResourceNotFoundException("Person","City",city);
        }

        return personList1;
    }

    @Override
    public Person getPersonByNameAndCity(String name, String city) {

        Person repositoryByNameAndCity = personRepository.findByNameAndCity(name, city);

        if (repositoryByNameAndCity==null){
            throw new ResourceNotFoundException("Person","PersonNameAndCity",name,city);
        }

        return repositoryByNameAndCity;
    }

    @Override
    public Person getPersonById(Integer id) {

        List<Person> personList = personRepository.findAll();

        Person person = personList.stream().filter(p -> p.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new ResourceNotFoundException("Person", "personId", id));

        return person;
    }
}
