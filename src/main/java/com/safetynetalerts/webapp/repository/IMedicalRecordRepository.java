package com.safetynetalerts.webapp.repository;

import com.safetynetalerts.webapp.model.MedicalRecord;

import java.io.IOException;
import java.util.List;

public interface IMedicalRecordRepository {

    List<MedicalRecord> getAllMedicalRecords();

    MedicalRecord getMedicalRecords(String birthdate);

    MedicalRecord deleteMedicalRecords(MedicalRecord medicalRecord);

    MedicalRecord updateMedicalRecords(MedicalRecord medicalRecord);

    MedicalRecord saveMedicalRecords(MedicalRecord medicalRecord);

    int getAgeFromBirthdate(String birthdate);
}
