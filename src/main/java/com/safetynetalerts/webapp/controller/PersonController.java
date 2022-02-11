package com.safetynetalerts.webapp.controller;

import com.safetynetalerts.webapp.model.Person;
import com.safetynetalerts.webapp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
public class PersonController {

    @Autowired
    private PersonService personService;


    // http://localhost:8080/person
@GetMapping("/person")
    public List<Person> getAllPersons() throws IOException {
    return this.personService.getAllPersons();
}

}
