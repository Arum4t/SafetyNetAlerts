package com.safetynetalerts.webapp.Data;

import com.safetynetalerts.webapp.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonData {

    public Person onePerson(){

        Person person = new Person();
        person.setLastName("Boyd");
        person.setAddress("1509 Culver St");
        person.setEmail("jaboyd1@email.com");
        person.setFirstName("John");
        person.setPhone("841-874-6512");
        person.setZip("97451");
        person.setCity("Culver");

        return person;
    }
    public List<Person> family(){

        List<Person> personList = new ArrayList<>();
        Person person = new Person();
        person.setLastName("Boyd");
        person.setAddress("1509 Culver St");
        person.setEmail("jaboyd1@email.com");
        person.setFirstName("John");
        person.setPhone("841-874-6512");
        person.setZip("97451");
        person.setCity("Culver");
        personList.add(person);
        Person person2 = new Person();
        person2.setLastName("Boyd");
        person2.setAddress("1509 Culver St");
        person2.setEmail("drk1@email.com");
        person2.setFirstName("Jacob");
        person2.setPhone("841-874-6513");
        person2.setZip("97451");
        person2.setCity("Culver");
        personList.add(person2);
        Person person3 = new Person();
        person3.setLastName("Boyd");
        person3.setAddress("1509 Culver St");
        person3.setEmail("tenz1@email.com");
        person3.setFirstName("Tenley");
        person3.setPhone("841-874-6512");
        person3.setZip("97451");
        person3.setCity("Culver");
        personList.add(person3);
        Person person4 = new Person();
        person4.setLastName("Boyd");
        person4.setAddress("1509 Culver St");
        person4.setEmail("jaboyd2@email.com");
        person4.setFirstName("Roger");
        person4.setPhone("841-874-6512");
        person4.setZip("97451");
        person4.setCity("Culver");
        personList.add(person4);
        Person person5 = new Person();
        person5.setLastName("Boyd");
        person5.setAddress("1509 Culver St");
        person5.setEmail("jaboyd3@email.com");
        person5.setFirstName("Felicia");
        person5.setPhone("841-874-6544");
        person5.setZip("97451");
        person5.setCity("Culver");
        personList.add(person5);
        return personList;
    }
    public Person createPerson(){
        Person person = new Person();
        person.setLastName("Schmidt");
        person.setAddress("22 rue des écureuils");
        person.setEmail("albert-s@email.com");
        person.setFirstName("Albert");
        person.setPhone("842-872-6222");
        person.setZip("67000");
        person.setCity("Strasbourg");
       return person;
    }
    public Person personUpdate(){
        Person person = new Person();
        person.setLastName("Boyd");
        person.setAddress("1509 Culver St");
        person.setEmail("jaboyd1@email.com");
        person.setFirstName("John");
        person.setPhone("841-874-6512");
        person.setZip("97451");
        person.setCity("Lille");

        return person;
    }
}
