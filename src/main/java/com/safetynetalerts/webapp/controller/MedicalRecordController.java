package com.safetynetalerts.webapp.controller;

import com.safetynetalerts.webapp.model.MedicalRecord;
import com.safetynetalerts.webapp.model.Person;
import com.safetynetalerts.webapp.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;


    // http://localhost:8080/medicalRecord
    @GetMapping("/medicalRecords")
    public List<MedicalRecord> getAllMedicalRecords(){
        return medicalRecordService.getAllMedicalRecords();
    }

    @PostMapping("/medicalRecords")
    public MedicalRecord saveMedicalRecords(@RequestBody MedicalRecord medicalRecord){
        return medicalRecordService.saveMedicalRecords(medicalRecord);
    }

    @PutMapping("/medicalRecords")
    public MedicalRecord updateMedicalRecords(@RequestBody MedicalRecord medicalRecord) {
        return medicalRecordService.updateMedicalRecords(medicalRecord);
    }

    @DeleteMapping("/medicalRecords/{firstName}/{lastName}")
    public String deleteMedicalRecords(@PathVariable("firstName") String firstName,@PathVariable("lastName")  String lastName) {
        Boolean ok = medicalRecordService.deleteMedicalRecords(firstName, lastName);
        if(ok){
            return "ok";
        }
        return "not ok";
    }
}
