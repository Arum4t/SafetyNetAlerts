package com.safetynetalerts.webapp.service;
import com.safetynetalerts.webapp.model.MedicalRecord;
import com.safetynetalerts.webapp.model.Person;

import java.util.List;

public interface IMedicalRecordService {

    List<MedicalRecord> getAllMedicalRecords();

    MedicalRecord getMedicalRecords(String birthdate);

    Boolean deleteMedicalRecords(String birthdate);

    MedicalRecord updateMedicalRecords(MedicalRecord medicalRecord);

    MedicalRecord saveMedicalRecords(MedicalRecord medicalRecord);

    List<String> getMedicationsFromPerson(Person person);

    List<String> getAllergiesFromPerson(Person person);


}
