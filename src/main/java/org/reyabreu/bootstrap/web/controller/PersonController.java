package org.reyabreu.bootstrap.web.controller;

import org.reyabreu.bootstrap.persistence.model.Person;
import org.reyabreu.bootstrap.persistence.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    public Iterable findAll() {
        return personRepository.findAll();
    }

    @GetMapping("/{lastName}")
    public List findByLastName(@PathVariable("lastName") String lastName) {
        return personRepository.findByLastName(lastName);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Person create(@RequestBody Person person) {
        return personRepository.save(person);
    }

}
