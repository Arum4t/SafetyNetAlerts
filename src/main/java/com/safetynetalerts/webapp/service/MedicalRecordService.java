package com.safetynetalerts.webapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynetalerts.webapp.controller.LoggingController;
import com.safetynetalerts.webapp.model.MedicalRecord;
import com.safetynetalerts.webapp.model.Person;
import com.safetynetalerts.webapp.repository.DataLoaderRepository;
import com.safetynetalerts.webapp.repository.MedicalRecordRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MedicalRecordService implements IMedicalRecordService {

    private static final Logger log = LoggerFactory.getLogger(LoggingController.class);

    private DataLoaderRepository dataLoaderRepository;
    private MedicalRecordRepository medicalRecordRepository;
    private ArrayList<MedicalRecord> medicalRecords;

    public MedicalRecordService() throws IOException {
        this.dataLoaderRepository = new DataLoaderRepository(new ObjectMapper());
        this.medicalRecordRepository = new MedicalRecordRepository(new ArrayList<>(this.dataLoaderRepository.getResponse().getMedicalrecords()));
    }

    @Override
    public List<MedicalRecord> getAllMedicalRecords(){
        return this.medicalRecordRepository.getAllMedicalRecords();

    }

    @Override
    public MedicalRecord getMedicalRecords(String birthdate) {
       return this.medicalRecordRepository.getMedicalRecords(birthdate);
    }

    @Override
    public Boolean deleteMedicalRecords(String birthdate) {
        MedicalRecord medicalRecord = getMedicalRecords(birthdate);
        if(medicalRecord == null){
            return false;
        }
        this.medicalRecordRepository.deleteMedicalRecords(medicalRecord);
        return true;
    }

    // TODO: finish service update MedicalRecord
    @Override
    public MedicalRecord updateMedicalRecords(MedicalRecord medicalRecord) {
        return null;
    }

    @Override
    public MedicalRecord saveMedicalRecords(MedicalRecord medicalRecord) {
        if(getMedicalRecords(medicalRecord.getBirthdate()) != null){
            log.error("404, entity not found");
        }
        return this.medicalRecordRepository.saveMedicalRecords(medicalRecord);
    }

    @Override
    public List<String> getMedicationsFromPerson(Person person) throws IOException {
        for(MedicalRecord medicalRecord : dataLoaderRepository.getResponse().getMedicalrecords()){
            if (person.getFirstName().equals(medicalRecord.getFirstName()) &&
            person.getLastName().equals(medicalRecord.getLastName())){
                return medicalRecord.getMedications();
            }
        }
        return null;
    }

    @Override
    public List<String> getAllergiesFromPerson(Person person) throws IOException {
        for(MedicalRecord medicalRecord : dataLoaderRepository.getResponse().getMedicalrecords()){
            if (person.getFirstName().equals(medicalRecord.getFirstName()) &&
                    person.getLastName().equals(medicalRecord.getLastName())){
                return medicalRecord.getAllergies();
            }
        }
        return null;
    }


}
