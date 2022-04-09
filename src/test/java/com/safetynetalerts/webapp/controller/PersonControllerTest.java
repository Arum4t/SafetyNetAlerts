package com.safetynetalerts.webapp.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynetalerts.webapp.Data.PersonData;
import com.safetynetalerts.webapp.model.specific.ChildAlert;
import com.safetynetalerts.webapp.model.specific.PersonAllInfo;
import com.safetynetalerts.webapp.model.specific.PersonInfoByFloodZone;
import com.safetynetalerts.webapp.model.Person;
import com.safetynetalerts.webapp.model.specific.PersonFireZoneResponse;
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
import java.util.Map;


@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PersonControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    @Test
    public void getAllPersons() throws Exception {
        List<Person> allPersonList = PersonData.allPerson();

    MvcResult result = mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/persons")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn();
    List<Person> allPersonListResult = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Person>>(){});
    Assertions.assertEquals(allPersonList,allPersonListResult);

    }
    @Test
    public void createPerson() throws Exception {
        Person person = PersonData.createPerson();
        String jsonPersonToAdd = objectMapper.writeValueAsString(person);

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/persons")
                        .content(jsonPersonToAdd)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();

        Person personResult = objectMapper.readValue(result.getResponse().getContentAsString(), Person.class);
        Assertions.assertEquals(person,personResult);
    }

    @Test
    public void deletePerson() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/persons/{firstName}/{lastName}","Warren", "Zemicks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.content().string("ok"))
                        .andReturn();

    }
    @Test
    public void updatePerson() throws Exception {
        Person person = PersonData.personCityUpdate();
        String jsonPersonToUpdate = objectMapper.writeValueAsString(person);

        MvcResult result = mvc.perform(MockMvcRequestBuilders.put("/persons")
                        .content(jsonPersonToUpdate)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();

        Person personResult = objectMapper.readValue(result.getResponse().getContentAsString(), Person.class);
        Assertions.assertEquals(person,personResult);
    }
    @Test
    public void childAlert() throws Exception {
        Map<String, List<ChildAlert>> childList = PersonData.listChildAlert();
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/childAlert?address={address}", "1509 Culver St")
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();
        Map<String, List<ChildAlert>> childListResult = objectMapper.readValue(result.getResponse().getContentAsString(),
                    new TypeReference<Map<String, List<ChildAlert>>>(){});
        Assertions.assertEquals(childList,childListResult);
    }
    @Test
    public void communityEmail() throws Exception {
    List<String> communityEmail = PersonData.getEmailByCity();
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/communityEmail?city={city}", "Culver")
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();
        List<String> communityEmailResult = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<String>>(){});
        Assertions.assertEquals(communityEmail,communityEmailResult);
    }
    @Test
    public void personAllInfo() throws Exception {
        List<PersonAllInfo> personAllInfos = PersonData.getPersonAllInfo();
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/personInfo?firstName={firstName}&lastName={lastName}", "John", "Boyd")
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();
        List<PersonAllInfo> personAllInfosResult = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<PersonAllInfo>>(){});
        Assertions.assertEquals(personAllInfos,personAllInfosResult);

    }
    @Test
    public void fireZone() throws Exception {
        List<PersonFireZoneResponse> fireZoneResponses = PersonData.getFireZone();
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/fire?address={address}", "1509 Culver St")
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();
        List<PersonFireZoneResponse> fireZoneResponsesResult = objectMapper.readValue(result.getResponse().getContentAsString(),
                    new TypeReference<List<PersonFireZoneResponse>>(){});
        Assertions.assertEquals(fireZoneResponses,fireZoneResponsesResult);
    }
    @Test
    public void floodZone() throws Exception {
        Map<String, List<PersonInfoByFloodZone>> floodZones = PersonData.getFloodZone();
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/flood/stations?stations={stations}", "1,2")
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();
        Map<String, List<PersonInfoByFloodZone>> floodZonesResult = objectMapper.readValue(result.getResponse().getContentAsString(),
                    new TypeReference<Map<String, List<PersonInfoByFloodZone>>>(){});
        Assertions.assertEquals(floodZones,floodZonesResult);
    }
    @Test
    public void phoneAlert() throws Exception {
    List<String> phoneAlert = PersonData.getPhoneAlert();
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/phoneAlert?fireStation={firstStation}", 2)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();
        List<String> phoneAlertResult = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<String>>(){});
        Assertions.assertEquals(phoneAlert,phoneAlertResult);
    }
}
