package com.safetynetalerts.webapp.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynetalerts.webapp.model.Person;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private DataLoaderRepository dataLoaderRepository;


    @Before
    public void setUp() {

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
