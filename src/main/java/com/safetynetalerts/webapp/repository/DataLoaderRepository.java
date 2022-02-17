package com.safetynetalerts.webapp.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynetalerts.webapp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;

@Repository
public class DataLoaderRepository implements IDataLoaderRepository {

        private ObjectMapper objectMapper;
        private Response response;

        @Autowired
        public DataLoaderRepository (ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
        }

       @Override
        public Response getResponse() throws IOException {
            response = objectMapper.readValue(new File("src/main/resources/json_list.json"), Response.class);
            return response;
        }
}
