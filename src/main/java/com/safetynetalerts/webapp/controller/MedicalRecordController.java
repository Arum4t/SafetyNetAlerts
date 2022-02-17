package com.safetynetalerts.webapp.controller;

import com.safetynetalerts.webapp.model.MedicalRecord;
import com.safetynetalerts.webapp.model.Person;
import com.safetynetalerts.webapp.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;


    // http://localhost:8080/medicalRecord
    @GetMapping("/medicalRecord")
    public List<MedicalRecord> getAllMedicalRecords(){
        return medicalRecordService.getAllMedicalRecords();
    }

    // http://localhost:8080/medicalRecord/
    @GetMapping("/medicalRecord/{birthdate}")
    public MedicalRecord getMedicalRecord (@PathVariable String birthdate){
        return this.medicalRecordService.getMedicalRecord(birthdate);
    }
}
