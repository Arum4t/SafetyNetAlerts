package com.safetynetalerts.webapp.repository;

import com.safetynetalerts.webapp.model.MedicalRecord;

import java.io.IOException;
import java.util.List;

public interface IMedicalRecordRepository {

    List<MedicalRecord> getMedicalRecords();

    MedicalRecord getMedicalRecord(String birthdate);
}
