package com.safetynetalerts.webapp.service;
import com.safetynetalerts.webapp.model.MedicalRecord;
import com.safetynetalerts.webapp.model.Person;

import java.time.LocalDate;
import java.util.List;

public interface IMedicalRecordService {

    List<MedicalRecord> getAll();

    MedicalRecord getMedicalRecords(String birthdate);

    Boolean deleteMedicalRecords(String firstName, String lastName);

    MedicalRecord updateMedicalRecords(MedicalRecord medicalRecord);

    MedicalRecord saveMedicalRecords(MedicalRecord medicalRecord);

    List<String> getMedicationsFromPerson(Person person);

    List<String> getAllergiesFromPerson(Person person);

    int calculateAgeFromBirthdate(String birthdate);

}
