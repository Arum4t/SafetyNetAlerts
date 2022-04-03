package com.safetynetalerts.webapp.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynetalerts.webapp.Data.FireStationData;
import com.safetynetalerts.webapp.Data.PersonData;
import com.safetynetalerts.webapp.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class FireStationControllerTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    @Test
    public void getAllFireStation() throws Exception {
    List<FireStation> fireStations = FireStationData.allFireStation();
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/fireStations")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        List<FireStation> fireStationsResult = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<FireStation>>(){});
        Assertions.assertEquals(fireStations,fireStationsResult);
    }
    @Test
    public void updateFireStation() throws Exception {
        FireStation fireStation = FireStationData.updateFireStationAddress();
        String jsonFireStationToAdd = objectMapper.writeValueAsString(fireStation);

        MvcResult result = mvc.perform(MockMvcRequestBuilders.put("/fireStations")
                        .content(jsonFireStationToAdd)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        FireStation fireStationResult = objectMapper.readValue(result.getResponse().getContentAsString(), FireStation.class);
        Assertions.assertEquals(fireStation,fireStationResult);
    }
    @Test
    public void saveFireStation() throws Exception {
        FireStation fireStation = FireStationData.saveFireStation();
        String jsonFireStationToAdd = objectMapper.writeValueAsString(fireStation);

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/fireStations")
                        .content(jsonFireStationToAdd)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();

        FireStation mfireStationResult = objectMapper.readValue(result.getResponse().getContentAsString(), FireStation.class);
        Assertions.assertEquals(fireStation,mfireStationResult);
    }
    @Test
    public void deleteFireStation() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/fireStations/{station}",2)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.content().string("ok"))
                        .andReturn();
    }
    @Test
    public void personsInfoByStation() throws Exception {
    List<PersonFireStationResponse> personInfoByStationList = PersonData.getPersonsInfoByStation();
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/fireStations?stationNumber={stationNumber}", 2)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();
        List<PersonFireStationResponse> personInfoByStationListResult = objectMapper.readValue(result.getResponse().getContentAsString(),
                new TypeReference<List<PersonFireStationResponse>>(){});
        Assertions.assertEquals(personInfoByStationList,personInfoByStationListResult);
    }
}
