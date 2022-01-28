package com.example.emrestserver.repository;

import com.example.emrestserver.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person,Integer> {
    Optional<Person> findPersonByName(String name);
}
