package com.parshuram.OneToOneMapping.repository;

import com.parshuram.OneToOneMapping.entity.Passport;
import com.parshuram.OneToOneMapping.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PassportRepository extends JpaRepository<Passport,String> {

      List<Passport> findByNationality(String nationality);

}
