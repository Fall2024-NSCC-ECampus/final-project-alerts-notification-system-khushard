package com.example.safetynet.alerts.repositories;

import com.example.safetynet.alerts.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    List<Person> findByAddress(String address);
    List<Person> findByFirstNameAndLastName(String firstName, String lastName);
}
