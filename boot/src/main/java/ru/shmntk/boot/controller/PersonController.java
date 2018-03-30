package ru.shmntk.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shmntk.boot.entity.Person;
import ru.shmntk.boot.repository.PersonRepository;
import ru.shmntk.boot.util.annotations.RandomInt;

@RestController
@RequestMapping("/test")
public class PersonController {

    @RandomInt
    private Integer i;

    private final PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    public String test() {
        Person person = new Person();
        person.setLastName("Литвак");
        personRepository.save(person);
        return "Приветики " + i;
    }
}
