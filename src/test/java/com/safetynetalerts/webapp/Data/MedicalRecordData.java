package com.safetynetalerts.webapp.Data;

import com.safetynetalerts.webapp.model.MedicalRecord;

import com.safetynetalerts.webapp.model.Person;
import lombok.Data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.safetynetalerts.webapp.Data.PersonData.getJsonModel;


@Data
public class MedicalRecordData {


    public static MedicalRecord getMedicalRecords() {
        List<String> medications = new ArrayList<>();
        medications.add("aznol:350mg");
        medications.add("hydrapermazol:100mg");
        List<String> allergies = new ArrayList<>();
        allergies.add("nillacilan");
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("John");
        medicalRecord.setLastName("Boyd");
        medicalRecord.setMedications(medications);
        medicalRecord.setAllergies(allergies);
        medicalRecord.setBirthdate("03/06/1984");

        return medicalRecord;
    }

    public static MedicalRecord updateMedicalRecords() {
        List<String> medications = new ArrayList<>();
        medications.add("aznol:350mg");
        List<String> allergies = new ArrayList<>();
        allergies.add("nillacilan");
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("John");
        medicalRecord.setLastName("Boyd");
        medicalRecord.setMedications(medications);
        medicalRecord.setAllergies(allergies);
        medicalRecord.setBirthdate("03/06/1984");

        return medicalRecord;
    }


    public static MedicalRecord saveMedicalRecords() {
        List<String> medications = new ArrayList<>();
        medications.add("doliprane");
        List<String> allergies = new ArrayList<>();
        allergies.add("gluten");
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("Dupont");
        medicalRecord.setLastName("Dupont");
        medicalRecord.setMedications(medications);
        medicalRecord.setAllergies(allergies);
        medicalRecord.setBirthdate("03/07/1986");

        return medicalRecord;
    }

    public static List<MedicalRecord> allMedicalRecord() throws IOException {
        return getJsonModel().getAllMedicalRecord();
    }

}
