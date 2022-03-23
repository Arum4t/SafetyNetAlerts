package com.safetynetalerts.webapp.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;



@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class DataLoaderRepositoryTest {

    @Mock
    private ObjectMapper objectMapper;

    private DataLoaderRepository dataLoaderRepository;

    @BeforeEach
    public void setUp(){
        dataLoaderRepository = new DataLoaderRepository(objectMapper);
    }

    @Test
    public void getResponse() throws IOException {


    }
}
