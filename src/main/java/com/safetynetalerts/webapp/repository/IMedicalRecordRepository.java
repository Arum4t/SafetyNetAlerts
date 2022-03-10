package com.safetynetalerts.webapp.repository;

import com.safetynetalerts.webapp.model.MedicalRecord;
import com.safetynetalerts.webapp.model.Person;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface IMedicalRecordRepository {

    List<MedicalRecord> getAllMedicalRecords();

    MedicalRecord getMedicalRecords(String birthdate);

    MedicalRecord deleteMedicalRecords(MedicalRecord medicalRecord);

    MedicalRecord updateMedicalRecords(MedicalRecord medicalRecord);

    MedicalRecord saveMedicalRecords(MedicalRecord medicalRecord);

    MedicalRecord getMedicalRecordsByFirstNameAndLastName(String firstName, String lastName);

    default int calculateAgeFromBirthdate(LocalDate birthdate, LocalDate currentDate) {
        return 0;
    }


}
