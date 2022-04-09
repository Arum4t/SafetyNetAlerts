package com.safetynetalerts.webapp.controller;

import com.safetynetalerts.webapp.model.*;
import com.safetynetalerts.webapp.model.specific.ChildAlert;
import com.safetynetalerts.webapp.model.specific.PersonAllInfo;
import com.safetynetalerts.webapp.model.specific.PersonFireZoneResponse;
import com.safetynetalerts.webapp.model.specific.PersonInfoByFloodZone;
import com.safetynetalerts.webapp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@RestController
public class PersonController {

    @Autowired
    private PersonService personService;


    //http://localhost:8080/persons
    @GetMapping("/persons")
        public List<Person> getAllPersons(){
        return this.personService.getAll();
}

    //http://localhost:8080/communityEmail?city=Culver
    @GetMapping("/communityEmail")
        public List<String> getEmailByCity (@RequestParam String city){
        return this.personService.getEmailByCity(city);
    }
    //http://localhost:8080/persons
    @PostMapping("/persons")
        public Person createPerson(@RequestBody Person person){
        return personService.savePerson(person);
}

    //http://localhost:8080/persons
    @PutMapping("/persons")
        public Person updatePerson(@RequestBody Person person) {
        return personService.updatePerson(person);
    }

    //http://localhost:8080/persons/John/Boyd
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
    public Map<String, List<ChildAlert>> listChildAlert (@RequestParam String address) throws IOException {
        return this.personService.listChildAlert(address);
    }
    //http://localhost:8080/personInfo?firstName=John&lastName=Boyd
    @GetMapping("/personInfo")
    public List<PersonAllInfo> getPersonAllInfo (@RequestParam String firstName, String lastName) throws IOException {
        return this.personService.getPersonAllInfo(firstName, lastName);
    }
    //http://localhost:8080/phoneAlert?fireStation=2
    @GetMapping(value = "/phoneAlert", params = "fireStation")
    public List<String> getPhoneAlert (@RequestParam int fireStation) throws IOException {
        return personService.getPhoneAlert(fireStation);
    }
    //http://localhost:8080/fire?address=1509 Culver St
    @GetMapping("fire")
    public List<PersonFireZoneResponse> getFireZone(@RequestParam String address) throws IOException {
        return personService.getFireZone(address);
    }
    //http://localhost:8080/flood/stations?stations=1,2,3
    @GetMapping(value = "flood/stations", params = "stations")
    public Map<String, List<PersonInfoByFloodZone>> getFloodZone(@RequestParam List<Integer> stations) throws IOException {
        return personService.getFloodZone(stations);
    }

}
