package com.safetynetalerts.webapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynetalerts.webapp.model.MedicalRecord;
import com.safetynetalerts.webapp.model.Person;
import com.safetynetalerts.webapp.repository.DataLoaderRepository;
import com.safetynetalerts.webapp.repository.MedicalRecordRepository;

import com.safetynetalerts.webapp.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MedicalRecordService implements IMedicalRecordService {

    private static final Logger log = LoggerFactory.getLogger(MedicalRecordService.class);

    private DataLoaderRepository dataLoaderRepository;
    private MedicalRecordRepository medicalRecordRepository;
    private PersonRepository personRepository;
    private ArrayList<MedicalRecord> medicalRecords;

    public MedicalRecordService() throws IOException {
        this.dataLoaderRepository = new DataLoaderRepository(new ObjectMapper());
        this.medicalRecordRepository = new MedicalRecordRepository(new ArrayList<>(this.dataLoaderRepository.getResponse().getMedicalrecords()));
        this.personRepository = new PersonRepository(new ArrayList<>(this.dataLoaderRepository.getResponse().getPersons()));
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
    public Boolean deleteMedicalRecords(String firstName, String lastName){
        MedicalRecord medicalRecord = this.medicalRecordRepository.getMedicalRecordsByFirstNameAndLastName(firstName, lastName);
       if (medicalRecord != null){
           this.medicalRecordRepository.deleteMedicalRecords(medicalRecord);
           return true;
       }return false;
    }


    @Override
    public MedicalRecord updateMedicalRecords(MedicalRecord medicalRecord) {
        if(this.medicalRecordRepository.getMedicalRecordsByFirstNameAndLastName(medicalRecord.getFirstName(), medicalRecord.getLastName()) != null){
            log.info("Request successful");
            return this.medicalRecordRepository.updateMedicalRecords(medicalRecord);
        } log.error("MedicalRecords not found");
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
    public List<String> getMedicationsFromPerson(Person person){
        for(MedicalRecord medicalRecord : this.medicalRecords){
            if (person.getFirstName().equals(medicalRecord.getFirstName()) &&
                    person.getLastName().equals(medicalRecord.getLastName())){
                return medicalRecord.getMedications();
            }
        }
        return null;
    }
    @Override
    public List<String> getAllergiesFromPerson(Person person){
        for(MedicalRecord medicalRecord : this.medicalRecords){
            if (person.getFirstName().equals(medicalRecord.getFirstName()) &&
                    person.getLastName().equals(medicalRecord.getLastName())){
                return medicalRecord.getAllergies();
            }
        }
        return null;
    }

}
