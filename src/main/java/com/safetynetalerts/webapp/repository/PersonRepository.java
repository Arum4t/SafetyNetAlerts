package com.safetynetalerts.webapp.repository;

import com.safetynetalerts.webapp.controller.LoggingController;
import com.safetynetalerts.webapp.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Repository
public class PersonRepository implements IPersonRepository{

    private static final Logger log = LoggerFactory.getLogger(LoggingController.class);

    private ArrayList<Person> persons;

    public PersonRepository(ArrayList<Person> persons) {
        if(this.persons == null){
            this.persons = new ArrayList<>();
        }
        this.persons.addAll(persons);
    }

    @Override
    public List<Person> getPersons(){
        return this.persons;
    }

    @Override
    public Person getPerson(String email) {
        for (Person person : this.persons) {
            if(Objects.equals(person.getEmail(), email)){
                return person;
            }
        }
        return null;
    }

    @Override
    public Person savePerson(Person person) {
        this.persons.add(person);
        return person;
    }

    @Override
    public Person updatePerson(Person person) {
            // Si l'email existe
            //Set les nouvelles valeurs de cette personne

        person.setEmail(person.getEmail());
        person.setPhone(person.getPhone());
        person.setZip(person.getZip());
        person.setCity(person.getCity());
        person.setAddress(person.getAddress());
        person.setLastName(person.getLastName());
        person.setFirstName(person.getFirstName());


        /*this.persons.set(0, person);
        this.persons.set(1, person);
        this.persons.set(2, person);
        this.persons.set(3, person);
        this.persons.set(4, person);
        this.persons.set(5, person);
        this.persons.set(6, person);*/

    return person;
    }

    @Override
    public Person deletePerson(Person person) {

        this.persons.remove(person);
        return person;
    }

    @Override
    public List<Person> getPersonsByAddress(String address) {

        List<Person> personsByAddress = new ArrayList<>();

        for (Person person : this.persons) {
            if(Objects.equals(person.getAddress(), address)){
                personsByAddress.add(person);
            }
        }
        return personsByAddress;
    }

    @Override
    public List<String> getEmailByCity (String city) {
        List<String> emailCityList = new ArrayList<>();

        for (Person person : getPersons()) {
            if(person.getCity().compareTo(city) == 0){
              emailCityList.add(person.getEmail());
            }
        }
        if(emailCityList.isEmpty()){
            log.info("Request get email failed.");
        } return emailCityList;
        }

    }


