package com.example.emrestserver.service;

import com.example.emrestserver.entity.Person;
import com.example.emrestserver.repository.PersonRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Log4j2
@Service
public class AsyncPersonService {
    @Autowired
    private PersonRepository personRepository;

    @Async("customExecutor")
    public CompletableFuture<Optional<Person>> asyncGetPetByName(String name) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.warn(Thread.currentThread().getName());

        Optional<Person> petOptional = personRepository.findPersonByName(name);

        return CompletableFuture.completedFuture(petOptional);
    }
}
