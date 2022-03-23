package com.safetynetalerts.webapp.repository;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
public class PersonRepositoryTest {

    @Autowired
    private DataLoaderRepository dataLoaderRepository;


    @Before
    public void setUp(){
    }


    @Test
    void getAll() {
    }

    @Test
    void getPerson() {
    }

    @Test
    void savePerson() {
    }

    @Test
    void updatePerson() {
    }

    @Test
    void deletePerson() {
    }

    @Test
    void getPersonsByAddress() {
    }

    @Test
    void getEmailByCity() {
    }

    @Test
    void getPersonByFirstNameAndLastName() {
    }
}
