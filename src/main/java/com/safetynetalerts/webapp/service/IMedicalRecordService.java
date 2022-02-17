package com.safetynetalerts.webapp.service;

import com.safetynetalerts.webapp.model.MedicalRecord;

import java.util.List;

public interface IMedicalRecordService {

    List<MedicalRecord> getAllMedicalRecords();

    MedicalRecord getMedicalRecord(String birthdate);
}
