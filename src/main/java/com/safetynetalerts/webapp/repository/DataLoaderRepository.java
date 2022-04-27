package com.safetynetalerts.webapp.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynetalerts.webapp.model.Response;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;

@Repository
public class DataLoaderRepository implements IDataLoaderRepository {

    private static final Logger logger = LoggerFactory.getLogger(DataLoaderRepository.class);

        private ObjectMapper objectMapper;
        private Response response;

        @Autowired
        public DataLoaderRepository (ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
        }

       @Override
        public Response getResponse(){
            if(response != null){
                return response;
            }
            try{
                response = objectMapper.readValue(new File("src/main/resources/json_list.json"), Response.class);
                logger.debug("Json correctly mapped!");
            } catch (IOException e){
                logger.error("Error while JSON mapping!", e);
                throw new RuntimeException(e);
            }
            return response;
       }
}
