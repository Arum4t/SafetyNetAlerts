package com.safetynetalerts.webapp.controller;

import com.safetynetalerts.webapp.model.Child;
import com.safetynetalerts.webapp.model.Person;
import com.safetynetalerts.webapp.model.PersonInfo;
import com.safetynetalerts.webapp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
public class PersonController {

    @Autowired
    private PersonService personService;


    // http://localhost:8080/persons
    @GetMapping("/persons")
        public List<Person> getAllPersons(){
        return this.personService.getAllPersons();
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

    @DeleteMapping("/persons/{firstName}/{lastName}")
    public String deletePerson (@PathVariable("firstName") String firstName,@PathVariable("lastName") String lastName) {
        Boolean ok = personService.deletePerson(firstName, lastName);
        if(ok){
            return "ok";
        }
        return "not ok";
    }
    //http://localhost:8080/childAlert?address=1509 Culver St
    @GetMapping("/childAlert")
    public Map<String, List<Child>> listChildAlert (@RequestParam String address){
        return this.personService.listChildAlert(address);
    }
    //http://localhost:8080/personInfo?firstName=John&lastName=Boyd
    @GetMapping("/personInfo")
    public List<PersonInfo> getPersonAllInfo (@RequestParam String firstName, String lastName){
        return this.personService.getPersonAllInfo(firstName, lastName);
    }
}
