package com.safetynetalerts.webapp.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynetalerts.webapp.model.Child;
import com.safetynetalerts.webapp.model.MedicalRecord;
import com.safetynetalerts.webapp.model.Person;
import com.safetynetalerts.webapp.repository.DataLoaderRepository;
import com.safetynetalerts.webapp.repository.MedicalRecordRepository;
import com.safetynetalerts.webapp.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;


@Service
public class PersonService implements IPersonService {

    private static final Logger log = LoggerFactory.getLogger(PersonService.class);

    private DataLoaderRepository dataLoaderRepository;
    private PersonRepository personRepository;
    private MedicalRecordRepository medicalRecordRepository;
    private ArrayList<Person> persons;
    private ArrayList<MedicalRecord> medicalRecords;

    public PersonService() throws IOException {
        this.dataLoaderRepository = new DataLoaderRepository(new ObjectMapper());
        this.personRepository = new PersonRepository(new ArrayList<>(this.dataLoaderRepository.getResponse().getPersons()));
        this.medicalRecordRepository = new MedicalRecordRepository(new ArrayList<>(this.dataLoaderRepository.getResponse().getMedicalrecords()));
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
        // tricky, toujours en partant de l'email. Mettre à jours les infos
        if (getPerson(person.getEmail()) != null) {
            return this.personRepository.updatePerson(person);

        }
        log.error("Person not found");
        return null;
    }

    @Override
    public Boolean deletePerson(String email) {
        Person person = getPerson(email);
        if (person == null) {
           return false;
        }
        this.personRepository.deletePerson(person);
        return true;
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
    public MedicalRecord getPersonMedicalRecords(String firstName, String lastName) {
        for (MedicalRecord medicalRecords : this.medicalRecords) {
            if (Objects.equals(firstName, medicalRecords.getFirstName()) &&
                    Objects.equals(lastName, medicalRecords.getLastName())) {
                return medicalRecords;
            }

        }
        return null;
    }

    @Override
    public Map<String, List<Child>> listChildAlert () {
        List<Person> persons = this.personRepository.getPersons();

        Map<String, List<Child>> childFamily = new HashMap<>(); // HERE !! Tableau famille enfants

        for (Person person : persons) {
            // 1. recuperer le nom de famille
            String familyName = person.getLastName();

            // 2. recuperer le medical record
            MedicalRecord medicalRecord = this.medicalRecordRepository.getMedicalRecordsByFirstNameAndLastName(person.getFirstName(), person.getLastName());

            // 3. verifier si il a -18 ans. ToDo :: code birthdate() method ..
            if (2022 - medicalRecord.getBirthdate() < 18) {
                // 4a. verifie si la famille exist, je l'ajoute
                List<Child> childrenFamily = childFamily.get(familyName);
                if(childrenFamily != null) {
                    Child child = new Child();
                    child.setAge();
                    childrenFamily.add(child);
                    continue;
                }
                // 4b. verifie si la famille exist PAS, je la cree
                childrenFamily = new ArrayList<>();
                Child child = new Child();
                child.setAge();
                childrenFamily.add(child);
                childFamily.putIfAbsent(familyName, childrenFamily);
            }
        }

        return childFamily;
    }

    /*
    * {"schnurr": [], "bini": '[]}
    *
    * */


}