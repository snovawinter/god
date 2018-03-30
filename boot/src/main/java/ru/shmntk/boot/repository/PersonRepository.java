package ru.shmntk.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shmntk.boot.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
