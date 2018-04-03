package ru.shmntk.god.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shmntk.god.boot.entity.Person;
import ru.shmntk.god.boot.repository.PersonRepository;

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
    @PreAuthorize("hasRole('ROLE_USER')")
    public Optional<Person> getPerson(@PathVariable Integer personId) {
	return personRepository.findById(personId);
    }
}
