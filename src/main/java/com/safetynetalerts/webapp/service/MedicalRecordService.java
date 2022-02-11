package com.safetynetalerts.webapp.service;

import com.safetynetalerts.webapp.model.MedicalRecord;
import com.safetynetalerts.webapp.repository.MedicalRecordRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Data
@Service
public class MedicalRecordService {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    public List<MedicalRecord> getAllMedicalRecords() throws IOException {
        return medicalRecordRepository.getMedicalRecords();

    }
}
