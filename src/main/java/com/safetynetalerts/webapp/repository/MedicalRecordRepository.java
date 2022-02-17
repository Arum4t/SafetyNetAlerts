package com.safetynetalerts.webapp.repository;

import com.safetynetalerts.webapp.model.MedicalRecord;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class MedicalRecordRepository implements IMedicalRecordRepository {

    private ArrayList<MedicalRecord> medicalRecords;

    public MedicalRecordRepository(ArrayList<MedicalRecord> medicalRecords) {
        this.medicalRecords = medicalRecords;
    }

    @Override
    public List<MedicalRecord> getMedicalRecords(){
        return this.medicalRecords;
    }

    @Override
    public MedicalRecord getMedicalRecord(String birthdate) {
        for (MedicalRecord medicalRecord : this.medicalRecords) {
            if (Objects.equals(medicalRecord.getBirthdate(), birthdate)) {
                return medicalRecord;
            }
        }
        return null;
    }
}
