package com.safetynetalerts.webapp.repository;


import com.safetynetalerts.webapp.model.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
public class DataLoaderRepositoryTest {

    @Autowired
    DataLoaderRepository dataLoaderRepository;

    public Response response;

    @Test
    void getResponse() {

    }
}
