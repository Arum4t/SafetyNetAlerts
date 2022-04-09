package com.safetynetalerts.webapp.repository;

import com.safetynetalerts.webapp.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PersonRepository implements IPersonRepository{

    private static final Logger logger = LoggerFactory.getLogger(PersonRepository.class);

    private ArrayList<Person> persons;

    public PersonRepository(ArrayList<Person> persons) {
        if(this.persons == null){
            this.persons = new ArrayList<>();
        }
        this.persons.addAll(persons);
    }

    @Override
    public List<Person> getAll(){
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

        this.persons.set(getAll().indexOf(getPerson(person.getEmail())), person);
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
                logger.info("Request get person by address successful!");
                personsByAddress.add(person);
            }

        }
        if(personsByAddress.isEmpty()){
            logger.info("Request person failed.");
        }return personsByAddress;
    }

    @Override
    public List<String> getEmailByCity (String city) {
        List<String> emailCityList = new ArrayList<>();

        for (Person person : getAll()) {
            if(person.getCity().compareTo(city) == 0){
                logger.info("Request get email successful!");
              emailCityList.add(person.getEmail());
            }
        }
        if(emailCityList.isEmpty()){
            logger.info("Request get email failed.");
        } return emailCityList;
        }

    public List<Person> getPersonByFirstNameAndLastName (String firstName, String lastName){
        List<Person> personByFirstNameAndLastName = new ArrayList<>();
        for (Person person : this.persons){
            if(person.getFirstName() != null &&  person.getLastName() != null){
                logger.info("Request get person successful!");
                personByFirstNameAndLastName.add(person);
            }
        }
        if(personByFirstNameAndLastName.isEmpty()){
            logger.info("Request get person failed.");
        }
        return personByFirstNameAndLastName;
    }

}


