package ru.shmntk.boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shmntk.boot.util.annotations.RandomInt;

@RestController
@RequestMapping("/test")
public class TestController {

    @RandomInt
    private Integer i;

    @GetMapping
    public String test() {
        return "Приветики " + i;
    }
}
