package ru.shmntk.god.boot.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PERSON")
@SequenceGenerator(name = "PERSON_SEQ", sequenceName = "PERSON_SEQ", allocationSize = 1)
@Data
public class Person implements Serializable {

    @Id
    @GeneratedValue(generator = "PERSON_SEQ")
    @Column(name = "PERSON_ID")
    private Integer personId;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "MIDDLE_NAME")
    private String middleName;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "EMAIL")
    private String email;
}
