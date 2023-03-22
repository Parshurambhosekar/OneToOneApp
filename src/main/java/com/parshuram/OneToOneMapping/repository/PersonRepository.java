package com.parshuram.OneToOneMapping.repository;

import com.parshuram.OneToOneMapping.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Integer> {

        Person findByName(String name);

        Person findByNameAndCity(String name,String city);
}
