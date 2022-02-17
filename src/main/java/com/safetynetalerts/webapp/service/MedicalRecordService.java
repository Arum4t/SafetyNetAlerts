package com.safetynetalerts.webapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynetalerts.webapp.model.MedicalRecord;
import com.safetynetalerts.webapp.repository.DataLoaderRepository;
import com.safetynetalerts.webapp.repository.MedicalRecordRepository;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MedicalRecordService implements IMedicalRecordService {

    private DataLoaderRepository dataLoaderRepository;
    private MedicalRecordRepository medicalRecordRepository;
    private ArrayList<MedicalRecord> medicalRecords;

    public MedicalRecordService() throws IOException {
        this.dataLoaderRepository = new DataLoaderRepository(new ObjectMapper());
        if(this.medicalRecords == null){
            this.medicalRecords = new ArrayList<>();
        }
        this.medicalRecords.addAll(new ArrayList<>(this.dataLoaderRepository.getResponse().getMedicalrecords()));
        this.medicalRecordRepository = new MedicalRecordRepository(this.medicalRecords);
    }


    @Override
    public List<MedicalRecord> getAllMedicalRecords(){
        return medicalRecordRepository.getMedicalRecords();

    }

    @Override
    public MedicalRecord getMedicalRecord(String birthdate) {
       return this.medicalRecordRepository.getMedicalRecord(birthdate);
    }

}
