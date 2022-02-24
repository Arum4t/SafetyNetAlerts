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


    // http://localhost:8080/persons
    @GetMapping("/persons")
        public List<Person> getAllPersons(){
        return this.personService.getAllPersons();
}
    // http://localhost:8080/person?email=drk@email.com
    @GetMapping("/person")
    public Person getPerson(@RequestParam String email){
        return this.personService.getPerson(email);
    }

    // http://localhost:8080/persons/address?address=947 E. Rose Dr
    @GetMapping("/persons/address")
        public List<Person> getPersonsByAddress(@RequestParam String address){
        return this.personService.getPersonsByAddress(address);
    }
    // http://localhost:8080/communityEmail?city=Culver
    @GetMapping("/communityEmail")
        public List<String> getEmailByCity (@RequestParam String city){
        return this.personService.getEmailByCity(city);
    }
    @PostMapping("/persons")
        public Person createPerson(@RequestBody Person person){
        return personService.savePerson(person);
}
    @PutMapping("/persons")
        public Person updatePerson(@RequestBody Person person) {
        return personService.updatePerson(person);
    }

    @DeleteMapping("/persons/{email}")
    public String deletePerson(@PathVariable("email") String email) {
        Boolean ok = personService.deletePerson(email);
        if(ok){
            return "ok";
        }
        return "not ok";
    }

}
