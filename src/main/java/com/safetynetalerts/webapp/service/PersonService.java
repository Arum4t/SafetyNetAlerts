package com.safetynetalerts.webapp.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynetalerts.webapp.model.*;
import com.safetynetalerts.webapp.model.DTO.*;
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
    public List<Person> getAll() {
        return this.personRepository.getAll();
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
        if (getPerson(person.getEmail()) != null) {
            log.info("Request successful");
            return this.personRepository.updatePerson(person);
        }
        log.error("Person not found");

        return null;
    }

    @Override
    public Boolean deletePerson(String firstName, String lastName) {
        List<Person> personToDelete = this.personRepository.getPersonByFirstNameAndLastName(firstName, lastName);
        for (Person person : personToDelete) {
            if (Objects.equals(person.getLastName(), lastName) && Objects.equals(person.getFirstName(), firstName)) {
                personToDelete.add(person);
                this.personRepository.deletePerson(person);
                return true;
            }
            return false;
        }
        return null;
    }

    @Override
    public List<Person> getPersonsByAddress(String address) {
        return this.personRepository.getPersonsByAddress(address);
    }

    @Override
    public List<String> getEmailByCity(String city) {
        return this.personRepository.getEmailByCity(city);
    }

    @Override
    public Map<String, List<ChildAlert>> listChildAlert(String address) throws IOException {
        List<Person> persons = this.personRepository.getPersonsByAddress(address);

        Map<String, List<ChildAlert>> childFamily = new HashMap<>(); // HERE !! Tableau famille enfants


        for (Person person : persons) {
            // 1. recuperer le nom de famille
            String familyName = person.getLastName();

            // 2. recuperer le medical record
            MedicalRecord medicalRecord = this.medicalRecordRepository.getMedicalRecordsByFirstNameAndLastName(person.getFirstName(), person.getLastName());
            String birthdate = this.medicalRecordRepository.getMedicalRecordsByFirstNameAndLastName(person.getFirstName(), person.getLastName()).getBirthdate();
            MedicalRecordService calculateAge = new MedicalRecordService();
            int age = calculateAge.calculateAgeFromBirthdate(birthdate);

            // 3. verifier si il a -18 ans.
            if (age < 18) {

                // 4a. verifie si la famille exist, je l'ajoute
                List<ChildAlert> childrenFamily = childFamily.get(familyName);
                if (childrenFamily != null) {
                    ChildAlert childAlert = new ChildAlert();
                    childAlert.setFirstName(person.getFirstName());
                    childAlert.setLastName(person.getLastName());
                    childAlert.setAge(age);
                    childrenFamily.add(childAlert);
                    continue;
                }
                // 4b. verifie si la famille exist PAS, je la cree
                childrenFamily = new ArrayList<>();
                ChildAlert childAlert = new ChildAlert();
                childAlert.setFirstName(person.getFirstName());
                childAlert.setLastName(person.getLastName());
                childAlert.setAge(age);
                childrenFamily.add(childAlert);
                childFamily.putIfAbsent(familyName, childrenFamily);
            }

        }

        return childFamily;
    }

    @Override
    public List<PersonAllInfo> getPersonAllInfo(String firstName, String lastName) throws IOException {
        List<Person> persons = this.personRepository.getPersonByFirstNameAndLastName(firstName, lastName);
        List<PersonAllInfo> allPersonAllInfo = new ArrayList<>();
        for (Person person : persons) {
            MedicalRecord medicalRecord = this.medicalRecordRepository.getMedicalRecordsByFirstNameAndLastName(person.getFirstName(), person.getLastName());
            String birthdate = this.medicalRecordRepository.getMedicalRecordsByFirstNameAndLastName(person.getFirstName(), person.getLastName()).getBirthdate();
            MedicalRecordService calculateAge = new MedicalRecordService();
            int age = calculateAge.calculateAgeFromBirthdate(birthdate);

            if (Objects.equals(person.getLastName(), lastName)) {
                PersonAllInfo personAllInfo = new PersonAllInfo();
                personAllInfo.setAddress(person.getAddress());
                personAllInfo.setFirstName(person.getFirstName());
                personAllInfo.setAllergies(medicalRecord.getAllergies());
                personAllInfo.setMedications(medicalRecord.getMedications());
                personAllInfo.setLastName(person.getLastName());
                personAllInfo.setEmail(person.getEmail());
                personAllInfo.setAge(age);
                allPersonAllInfo.add(personAllInfo);

            }
        }

        return allPersonAllInfo;
    }

    @Override
    public List<PersonFireStationResponse> getPersonsInfoByStation(int station) throws IOException {
        List<Person> persons = getPersonByFireStation(station);
        List<PersonFireStationResponse> personFireStationResponses = new ArrayList<>();
        List<PersonInfoByStation> personList = new ArrayList<>();

        int adult = 0;
        int child = 0;

        for (Person person : persons) {

            MedicalRecord medicalRecord = this.medicalRecordRepository.getMedicalRecordsByFirstNameAndLastName(person.getFirstName(), person.getLastName());
            String birthdate = this.medicalRecordRepository.getMedicalRecordsByFirstNameAndLastName(person.getFirstName(), person.getLastName()).getBirthdate();
            MedicalRecordService calculateAge = new MedicalRecordService();
            int age = calculateAge.calculateAgeFromBirthdate(birthdate);

            if (age > 18) {
                adult++;
            }
            if (age < 18) {
                child++;
            }
            PersonInfoByStation personInfoByStation = new PersonInfoByStation();
            personInfoByStation.setPhone(person.getPhone());
            personInfoByStation.setFirstName(person.getFirstName());
            personInfoByStation.setLastName(person.getLastName());
            personInfoByStation.setAddress(person.getAddress());
            personList.add(personInfoByStation);

        }

        PersonFireStationResponse personFireStationResponse = new PersonFireStationResponse();
        personFireStationResponse.setPersons(personList);
        personFireStationResponse.setAdult(adult);
        personFireStationResponse.setChild(child);
        personFireStationResponses.add(personFireStationResponse);

        return personFireStationResponses;

    }

    @Override
    public List<Person> getPersonByFireStation(int station) throws IOException {
        List<Person> persons = this.personRepository.getAll();
        FireStation fireStations = this.fireStationRepository.getFireStation(station);
        List<Person> personByFireStationAddress = new ArrayList<>();
        FireStationService fireStationService = new FireStationService();

        for (Person person : persons) {


            if (fireStationService.getStationNumberPerson(person.getAddress()) == fireStations.getStation()) {
                personByFireStationAddress.add(person);
            }
        }
        return personByFireStationAddress;
    }

    @Override
    public List<String> getPhoneAlert(int fireStationNumber) throws IOException {
        List<Person> persons = getPersonByFireStation(fireStationNumber);
        List<String> phoneList = new ArrayList<>();

        for (Person person : persons) {
            phoneList.add(person.getPhone());
        }

        return phoneList;
    }

    @Override
    public List<PersonFireZoneResponse> getFireZone(String address) throws IOException {
        List<Person> persons = this.personRepository.getPersonsByAddress(address);
        FireStation fireStations = this.fireStationRepository.getFireStationByAddress(address);
        List<PersonFireZoneResponse> fireZones = new ArrayList<>();
        List<PersonInfoByFireZone> fireZoneList = new ArrayList<>();

        for (Person person : persons) {


            MedicalRecord medicalRecord = this.medicalRecordRepository.getMedicalRecordsByFirstNameAndLastName(person.getFirstName(), person.getLastName());
            String birthdate = this.medicalRecordRepository.getMedicalRecordsByFirstNameAndLastName(person.getFirstName(), person.getLastName()).getBirthdate();
            MedicalRecordService calculateAge = new MedicalRecordService();
            int age = calculateAge.calculateAgeFromBirthdate(birthdate);


            PersonInfoByFireZone personInfoByFireZone = new PersonInfoByFireZone();
            personInfoByFireZone.setAge(age);
            personInfoByFireZone.setPhone(person.getPhone());
            personInfoByFireZone.setLastName(person.getLastName());
            personInfoByFireZone.setAllergies(medicalRecord.getAllergies());
            personInfoByFireZone.setMedications(medicalRecord.getMedications());
            fireZoneList.add(personInfoByFireZone);

        }
        PersonFireZoneResponse personFireZoneResponse = new PersonFireZoneResponse();
        personFireZoneResponse.setPersons(fireZoneList);
        personFireZoneResponse.setStation(fireStations.getStation());
        fireZones.add(personFireZoneResponse);

        return fireZones;
    }

    @Override
    public Map<String, List<PersonInfoByFloodZone>> getFloodZone(List<Integer> stations) throws IOException {

        List<Person> persons = this.personRepository.getAll();
        Map<String, List<PersonInfoByFloodZone>> foyer = new HashMap<>();
        FireStationService fireStationService = new FireStationService();

        for(Person person : persons) {

            String address = "Address : " + person.getAddress();

            MedicalRecord medicalRecord = this.medicalRecordRepository.getMedicalRecordsByFirstNameAndLastName(person.getFirstName(), person.getLastName());
            String birthdate = this.medicalRecordRepository.getMedicalRecordsByFirstNameAndLastName(person.getFirstName(), person.getLastName()).getBirthdate();
            MedicalRecordService calculateAge = new MedicalRecordService();
            int age = calculateAge.calculateAgeFromBirthdate(birthdate);

            if (stations.contains(fireStationService.getStationNumberPerson(person.getAddress()))) {

                List<PersonInfoByFloodZone> personInfoByFloodZones = foyer.get(address);
                if(personInfoByFloodZones != null){
                    PersonInfoByFloodZone personInfoByFloodZone = new PersonInfoByFloodZone();
                    personInfoByFloodZone.setPhone(person.getPhone());
                    personInfoByFloodZone.setAge(age);
                    personInfoByFloodZone.setLastName(person.getLastName());
                    personInfoByFloodZone.setPhone(person.getPhone());
                    personInfoByFloodZone.setAllergies(medicalRecord.getAllergies());
                    personInfoByFloodZone.setMedications(medicalRecord.getMedications());
                    personInfoByFloodZones.add(personInfoByFloodZone);
                    continue;
                }
                personInfoByFloodZones = new ArrayList<>();
                PersonInfoByFloodZone personInfoByFloodZone = new PersonInfoByFloodZone();
                personInfoByFloodZone.setPhone(person.getPhone());
                personInfoByFloodZone.setAge(age);
                personInfoByFloodZone.setLastName(person.getLastName());
                personInfoByFloodZone.setPhone(person.getPhone());
                personInfoByFloodZone.setAllergies(medicalRecord.getAllergies());
                personInfoByFloodZone.setMedications(medicalRecord.getMedications());
                personInfoByFloodZones.add(personInfoByFloodZone);
                foyer.putIfAbsent(address, personInfoByFloodZones);
            }

        }

        return foyer;
       }
}