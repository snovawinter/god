package ru.shmntk.god.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shmntk.god.boot.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
