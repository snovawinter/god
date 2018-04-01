package ru.shmntk.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shmntk.boot.entity.Person;
import ru.shmntk.boot.repository.PersonRepository;
import ru.shmntk.boot.util.annotations.RandomInt;

import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/{personId}")
    public Optional<Person> getPerson(@PathVariable Integer personId) {
        return personRepository.findById(personId);
    }
}
