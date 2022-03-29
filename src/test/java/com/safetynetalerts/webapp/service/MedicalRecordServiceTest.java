package com.safetynetalerts.webapp.service;

import com.safetynetalerts.webapp.Data.MedicalRecordData;
import com.safetynetalerts.webapp.model.MedicalRecord;
import com.safetynetalerts.webapp.model.Person;
import com.safetynetalerts.webapp.repository.DataLoaderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MedicalRecordServiceTest {

    @Autowired
    private DataLoaderRepository dataLoaderRepository;

    @Autowired
    private MedicalRecordService medicalRecordService;

    @BeforeEach
    public void setUp() throws IOException {
        MedicalRecordData medicalRecordData = new MedicalRecordData();
        medicalRecordService = new MedicalRecordService();
    }

    @Test
    public void getMedicalRecordsTest(){
        MedicalRecord medicalRecord = MedicalRecordData.getMedicalRecords();
        String birthdate = "03/06/1984";
        MedicalRecord current = medicalRecordService.getMedicalRecords(birthdate);
        Assertions.assertEquals(medicalRecord, current);
    }
    @Test
    public void deleteMedicalRecordsTest(){
        String firstName = "John";
        String lastName = "Boyd";
        Boolean current = medicalRecordService.deleteMedicalRecords(firstName, lastName);
        Assertions.assertTrue(current);
    }
    @Test
    public void updateMedicalRecordsTest() {
    // Une medication en moins => "aznol:350mg" au lieu de "aznol:350mg","hydrapermazol:100mg"
    MedicalRecord medicalRecord = MedicalRecordData.updateMedicalRecords();
    List<String> medication = new ArrayList<>();
    medication.add("aznol:350mg");
    MedicalRecord current = medicalRecordService.updateMedicalRecords(medicalRecord);
    Assertions.assertEquals(medication, current.getMedications());
    }
    @Test
    public void saveMedicalRecordsTest() {
        MedicalRecord medicalRecord = MedicalRecordData.saveMedicalRecords();
        MedicalRecord current = medicalRecordService.saveMedicalRecords(medicalRecord);
        Assertions.assertNotNull(current);
    }
    @Test
    public void calculateAgeFromBirthdateTest(){
    String birthdate = "03/06/1984";
    int current = medicalRecordService.calculateAgeFromBirthdate(birthdate);
    Assertions.assertEquals(38, current);
    }
}
