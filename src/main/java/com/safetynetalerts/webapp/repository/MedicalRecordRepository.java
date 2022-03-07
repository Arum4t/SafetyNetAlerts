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

    private static final Logger log = LoggerFactory.getLogger(MedicalRecordRepository.class);

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

    // TODO: finish update medicalRecords
    @Override
    public MedicalRecord updateMedicalRecords(MedicalRecord medicalRecord) {
        return null;
    }

    @Override
    public MedicalRecord saveMedicalRecords(MedicalRecord medicalRecord) {
        this.medicalRecords.add(medicalRecord);
        return medicalRecord;
    }


    public int calculateAgeFromBirthdate(String birthdate){
        LocalDate currentDate = LocalDate.now();
        if( birthdate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            formatter = formatter.withLocale(Locale.FRANCE);
            LocalDate birthDate = LocalDate.parse(birthdate, formatter);
            return Period.between(birthDate, currentDate).getYears();
        }
        return 0;
    }


}
