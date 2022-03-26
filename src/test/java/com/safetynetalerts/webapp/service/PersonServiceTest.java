package com.safetynetalerts.webapp.service;

import com.safetynetalerts.webapp.model.Person;
import com.safetynetalerts.webapp.repository.DataLoaderRepository;
import com.safetynetalerts.webapp.Data.PersonData;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class PersonServiceTest {

    @Autowired
    private DataLoaderRepository dataLoaderRepository;
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonData personData;

    @Before
    public void setUp(){
    }

    @Test
    void getPerson() {
        //La personne créer == la personne récupérer par la méthode via son email
        Person getPerson = personData.onePerson();
        String email = "jaboyd1@email.com";

        Person current = personService.getPerson(email);

        Assertions.assertEquals(getPerson, current);
        System.out.println(current);
        System.out.println(getPerson);
    }
    @Test
    void SavePerson() {
        //Personne qui n'existe pas dans le fichier Json. Si elle existe current == null.
        Person createPerson = personData.createPerson();

        Person current = personService.savePerson(createPerson);

        Assertions.assertNotNull(current);
    }
    @Test
    void UpdatePerson() {
        Person person = personData.personUpdate();

        Person current = personService.updatePerson(person);

        Assertions.assertEquals(person, current);
    }
    @Test
    void getPersonsByAddress() {
        List<Person> personList = personData.family();
        String address = "1509 Culver St";

        List<Person> current = personService.getPersonsByAddress(address);

        Assertions.assertEquals(personList, current);

    }
}
