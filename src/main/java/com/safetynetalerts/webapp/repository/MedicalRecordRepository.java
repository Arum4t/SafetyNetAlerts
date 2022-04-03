package com.safetynetalerts.webapp.repository;
import com.safetynetalerts.webapp.model.MedicalRecord;
import com.safetynetalerts.webapp.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

@Repository
public class MedicalRecordRepository implements IMedicalRecordRepository {

    private static final Logger logger = LoggerFactory.getLogger(MedicalRecordRepository.class);

    private ArrayList<MedicalRecord> medicalRecords;

    public MedicalRecordRepository(ArrayList<MedicalRecord> medicalRecords) {
        if(this.medicalRecords == null){
            this.medicalRecords = new ArrayList<>();
        }
        this.medicalRecords.addAll(medicalRecords);
    }

    @Override
    public List<MedicalRecord> getAllMedicalRecords(){
        return this.medicalRecords;
    }

    @Override
    public MedicalRecord getMedicalRecords(String birthdate) {
        for (MedicalRecord medicalRecord : this.medicalRecords){
            if(Objects.equals(medicalRecord.getBirthdate(), birthdate)){
                return medicalRecord;
            }
        }
        return null;
    }

    @Override
    public MedicalRecord deleteMedicalRecords(MedicalRecord medicalRecord) {
        this.medicalRecords.remove(medicalRecord);
        return medicalRecord;
    }

    @Override
    public MedicalRecord updateMedicalRecords(MedicalRecord medicalRecord) {
        this.medicalRecords.set(getAllMedicalRecords().indexOf(getMedicalRecordsByFirstNameAndLastName(medicalRecord.getFirstName(), medicalRecord.getLastName())), medicalRecord);
        return medicalRecord;
    }

    @Override
    public MedicalRecord saveMedicalRecords(MedicalRecord medicalRecord) {
        this.medicalRecords.add(medicalRecord);
        return medicalRecord;
    }

    @Override
    public MedicalRecord getMedicalRecordsByFirstNameAndLastName(String firstName, String lastName) {
        for (MedicalRecord medicalRecord : this.medicalRecords) {
            if(Objects.equals(medicalRecord.getFirstName(), firstName) && Objects.equals(medicalRecord.getLastName(), lastName)){
                return medicalRecord;
            }
        }
        return null;
    }




}
