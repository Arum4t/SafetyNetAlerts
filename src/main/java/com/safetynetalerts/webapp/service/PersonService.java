package com.safetynetalerts.webapp.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynetalerts.webapp.model.*;
import com.safetynetalerts.webapp.model.specific.*;
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

    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

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
            logger.info("Request save person failed");
        }
        logger.info("Request save person successful");
        return this.personRepository.savePerson(person);

    }


    @Override
    public Person updatePerson(Person person) {
        if (getPerson(person.getEmail()) != null) {
            logger.info("Request updatePerson successful");
            return this.personRepository.updatePerson(person);
        }
        logger.error("Person not found");

        return null;
    }

    @Override
    public Boolean deletePerson(String firstName, String lastName) {
        List<Person> personToDelete = this.personRepository.getPersonByFirstNameAndLastName(firstName, lastName);
        for (Person person : personToDelete) {
            this.personRepository.deletePerson(person);
            return true;
        }
        return false;
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
        Map<String, List<ChildAlert>> childFamily = new HashMap<>();

        for (Person person : persons) {
            String familyName = person.getLastName();
            MedicalRecord medicalRecord = this.medicalRecordRepository.getMedicalRecordsByFirstNameAndLastName(person.getFirstName(), person.getLastName());
            String birthdate = this.medicalRecordRepository.getMedicalRecordsByFirstNameAndLastName(person.getFirstName(), person.getLastName()).getBirthdate();
            MedicalRecordService calculateAge = new MedicalRecordService();
            int age = calculateAge.calculateAgeFromBirthdate(birthdate);

            if (age < 18) {

                List<ChildAlert> childrenFamily = childFamily.get(familyName);
                if (childrenFamily != null) {
                    ChildAlert childAlert = new ChildAlert();
                    childAlert.setFirstName(person.getFirstName());
                    childAlert.setLastName(person.getLastName());
                    childAlert.setAge(age);
                    childrenFamily.add(childAlert);
                    continue;
                }
                childrenFamily = new ArrayList<>();
                ChildAlert childAlert = new ChildAlert();
                childAlert.setFirstName(person.getFirstName());
                childAlert.setLastName(person.getLastName());
                childAlert.setAge(age);
                childrenFamily.add(childAlert);
                childFamily.putIfAbsent(familyName, childrenFamily);
            }
        }
        if(childFamily.isEmpty()){
            logger.info("Request get child alerts failed.");
            return childFamily;

        }logger.info("Request get child alerts successful!");
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
        if(allPersonAllInfo.isEmpty()){
            logger.info("Request get full information failed.");
            return allPersonAllInfo;
        }
        logger.info("Request get full information successful!");
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

        if(personList.isEmpty()){
            logger.info("Request info by station failed");
            return personFireStationResponses;
        }
        logger.info("Request info by station successful");
        return personFireStationResponses;

    }

    @Override
    public List<Person> getPersonByFireStation(int station) throws IOException {
        List<Person> persons = this.personRepository.getAll();
        FireStation fireStations = this.fireStationRepository.getFireStation(station);
        List<Person> personByFireStation = new ArrayList<>();
        FireStationService fireStationService = new FireStationService();

        for (Person person : persons) {


            if (fireStationService.getStationNumberByPersonAddress(person.getAddress()) == fireStations.getStation()) {
                personByFireStation.add(person);
            }
        }
        if(personByFireStation.isEmpty()){
            logger.info("Request person by firestation failed");
            return personByFireStation;
        }
        logger.info("Request person by firestation successful");
        return personByFireStation;
    }

    @Override
    public List<String> getPhoneAlert(int fireStationNumber) throws IOException {
        List<Person> persons = getPersonByFireStation(fireStationNumber);
        List<String> phoneList = new ArrayList<>();

        for (Person person : persons) {
            phoneList.add(person.getPhone());
        }
        if(phoneList.isEmpty()){
            logger.info("Request phoneList failed");
            return phoneList;
        }
        logger.info("Request phoneList successful");
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

        if(fireZoneList.isEmpty()){
            logger.info("Request fire zone failed");
            return fireZones;
        }
        logger.info("Request fire zone successful");
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

            if (stations.contains(fireStationService.getStationNumberByPersonAddress(person.getAddress()))) {
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
        if(foyer.isEmpty()){
            logger.info("Request flood zone failed");
            return foyer;
        }
        logger.info("Request flood zone successful");
        return foyer;
       }
}