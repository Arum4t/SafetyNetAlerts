package com.safetynetalerts.webapp.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynetalerts.webapp.model.*;
import com.safetynetalerts.webapp.repository.DataLoaderRepository;
import com.safetynetalerts.webapp.repository.FireStationRepository;
import com.safetynetalerts.webapp.repository.MedicalRecordRepository;
import com.safetynetalerts.webapp.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;


@Service
public class PersonService implements IPersonService {

    private static final Logger log = LoggerFactory.getLogger(PersonService.class);

    private DataLoaderRepository dataLoaderRepository;
    private PersonRepository personRepository;
    private MedicalRecordRepository medicalRecordRepository;
    private FireStationRepository fireStationRepository;
    private ArrayList<Person> persons;
    private ArrayList<MedicalRecord> medicalRecords;

    public PersonService() throws IOException {
        this.dataLoaderRepository = new DataLoaderRepository(new ObjectMapper());
        this.personRepository = new PersonRepository(new ArrayList<>(this.dataLoaderRepository.getResponse().getPersons()));
        this.medicalRecordRepository = new MedicalRecordRepository(new ArrayList<>(this.dataLoaderRepository.getResponse().getMedicalrecords()));
        this.fireStationRepository = new FireStationRepository(new ArrayList<>(this.dataLoaderRepository.getResponse().getFirestations()));
    }

    @Override
    public List<Person> getAllPersons() {
        return this.personRepository.getPersons();
    }

    @Override
    public Person getPerson(String email) {
        return this.personRepository.getPerson(email);
    }

    @Override
    public Person savePerson(Person person) {
        if (getPerson(person.getEmail()) != null) {
           /*
           throw new ResponseStatusException(
                  HttpStatus.NOT_FOUND, "entity not found"
            );*/
            log.error("404, entity not found");
        }
            return this.personRepository.savePerson(person);
        }


    @Override
    public Person updatePerson(Person person) {
        if(getPerson(person.getEmail()) != null){
            log.info("Request successful");
            return this.personRepository.updatePerson(person);
        }log.error("Person not found");

        return null;
    }

    @Override
    public Boolean deletePerson (String firstName, String lastName) {
        List<Person> personToDelete = this.personRepository.getPersonByFirstNameAndLastName(firstName, lastName);
        for(Person person : personToDelete){
            if(Objects.equals(person.getLastName(), lastName)&&Objects.equals(person.getFirstName(), firstName)){
                personToDelete.add(person);
                this.personRepository.deletePerson(person);
                return true;
            }return false;
        }
        return null;
    }

    @Override
    public List<Person> getPersonsByAddress(String address) {
        return this.personRepository.getPersonsByAddress(address);
    }

    @Override
    public List<String> getEmailByCity (String city) {
        return this.personRepository.getEmailByCity(city);
    }


    @Override
    public Map<String, List<Child>> listChildAlert (String address) {
        List<Person> persons = this.personRepository.getPersonsByAddress(address);

        Map<String, List<Child>> childFamily = new HashMap<>(); // HERE !! Tableau famille enfants


        for (Person person : persons) {
            // 1. recuperer le nom de famille
            String familyName = person.getLastName();

            // 2. recuperer le medical record
            MedicalRecord medicalRecord = this.medicalRecordRepository.getMedicalRecordsByFirstNameAndLastName(person.getFirstName(), person.getLastName());
            String birthdate = this.medicalRecordRepository.getMedicalRecordsByFirstNameAndLastName(person.getFirstName(), person.getLastName()).getBirthdate();
            int age = this.medicalRecordRepository.calculateAgeFromBirthdate(birthdate);

            // 3. verifier si il a -18 ans.
            if (age < 18) {

                // 4a. verifie si la famille exist, je l'ajoute
                List<Child> childrenFamily = childFamily.get(familyName);
                if(childrenFamily != null) {
                    Child child = new Child();
                    child.setFirstName(person.getFirstName());
                    child.setLastName(person.getLastName());
                    child.setAge(age);
                    childrenFamily.add(child);
                    continue;
                }
                // 4b. verifie si la famille exist PAS, je la cree
                childrenFamily = new ArrayList<>();
                Child child = new Child();
                child.setFirstName(person.getFirstName());
                child.setLastName(person.getLastName());
                child.setAge(age);
                childrenFamily.add(child);
                childFamily.putIfAbsent(familyName, childrenFamily);
            }

        }

        return childFamily;
    }

    @Override
    public List<PersonInfo> getPersonAllInfo(String firstName, String lastName) {
        List<Person> persons = this.personRepository.getPersonByFirstNameAndLastName(firstName, lastName);
        List<PersonInfo> allPersonInfo = new ArrayList<>();
        for (Person person : persons){
            MedicalRecord medicalRecord = this.medicalRecordRepository.getMedicalRecordsByFirstNameAndLastName(person.getFirstName(), person.getLastName());
            String birthdate = this.medicalRecordRepository.getMedicalRecordsByFirstNameAndLastName(person.getFirstName(), person.getLastName()).getBirthdate();
            int age = this.medicalRecordRepository.calculateAgeFromBirthdate(birthdate);

            if(Objects.equals(person.getLastName(), lastName)){
                PersonInfo personInfo = new PersonInfo();
                personInfo.setAddress(person.getAddress());
                personInfo.setFirstName(person.getFirstName());
                personInfo.setAllergies(medicalRecord.getAllergies());
                personInfo.setMedications(medicalRecord.getMedications());
                personInfo.setLastName(person.getLastName());
                personInfo.setEmail(person.getEmail());
                personInfo.setAge(age);
                allPersonInfo.add(personInfo);

            }
        }

        return allPersonInfo;
    }



}