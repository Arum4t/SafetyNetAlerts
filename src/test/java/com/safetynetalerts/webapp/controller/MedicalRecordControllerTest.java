package com.safetynetalerts.webapp.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynetalerts.webapp.Data.FireStationData;
import com.safetynetalerts.webapp.Data.MedicalRecordData;
import com.safetynetalerts.webapp.Data.PersonData;
import com.safetynetalerts.webapp.model.FireStation;
import com.safetynetalerts.webapp.model.MedicalRecord;
import com.safetynetalerts.webapp.model.Person;
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
public class MedicalRecordControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    @Test
    public void getAllMedicalRecord() throws Exception {
        List<MedicalRecord> medicalRecords = MedicalRecordData.allMedicalRecord();
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/medicalRecords")
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();
        List<MedicalRecord> medicalRecordsResult = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<MedicalRecord>>(){});
        Assertions.assertEquals(medicalRecords,medicalRecordsResult);
    }
    @Test
    public void updateMedicalRecord() throws Exception {
        MedicalRecord medicalRecord = MedicalRecordData.updateMedicalRecords();
        String jsonMedicalToAdd = objectMapper.writeValueAsString(medicalRecord);

        MvcResult result = mvc.perform(MockMvcRequestBuilders.put("/medicalRecords")
                        .content(jsonMedicalToAdd)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();

        MedicalRecord medicalRecordResult = objectMapper.readValue(result.getResponse().getContentAsString(), MedicalRecord.class);
        Assertions.assertEquals(medicalRecord,medicalRecordResult);
    }
    @Test
    public void saveMedicalRecord() throws Exception {
        MedicalRecord medicalRecord = MedicalRecordData.saveMedicalRecords();
        String jsonMedicalToAdd = objectMapper.writeValueAsString(medicalRecord);

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/medicalRecords")
                        .content(jsonMedicalToAdd)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();

        MedicalRecord medicalRecordResult = objectMapper.readValue(result.getResponse().getContentAsString(), MedicalRecord.class);
        Assertions.assertEquals(medicalRecord,medicalRecordResult);
    }
    @Test
    public void deleteMedicalRecord() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/medicalRecords/{firstName}/{lastName}","Warren", "Zemicks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string("ok"))
                .andReturn();
    }
}
