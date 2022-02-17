package com.safetynetalerts.webapp.controller;

import com.safetynetalerts.webapp.model.Person;
import com.safetynetalerts.webapp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class PersonController {

    @Autowired
    private PersonService personService;


    // http://localhost:8080/person
    @GetMapping("/person")
        public List<Person> getAllPersons(){
        return this.personService.getAllPersons();
}
    // http://localhost:8080/person/drk@email.com
    @GetMapping("/person/{email}")
    public Person getPerson(@PathVariable String email){
        return this.personService.getPerson(email);
    }

    @PostMapping("/person")
        public Person createPerson(@RequestBody Person person){
        return personService.savePerson(person);
}
    @PutMapping("/person")
        public Person updatePerson(@RequestBody Person person) {
        return personService.updatePerson();
    }

    @DeleteMapping("/person")
    public boolean deletePerson(@RequestBody Person person) {
        return personService.deletePerson();
    }

}
