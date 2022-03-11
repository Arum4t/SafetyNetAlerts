package com.safetynetalerts.webapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynetalerts.webapp.model.FireStation;
import com.safetynetalerts.webapp.model.MedicalRecord;
import com.safetynetalerts.webapp.model.Person;
import com.safetynetalerts.webapp.model.PersonStation;
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
public class FireStationService implements IFireStationService {

    private static final Logger log = LoggerFactory.getLogger(FireStationService.class);

    private DataLoaderRepository dataLoaderRepository;
    private FireStationRepository fireStationRepository;
    private PersonRepository personRepository;
    private MedicalRecordRepository medicalRecordRepository;
    private ArrayList<FireStation> fireStations;

    public FireStationService() throws IOException {
        this.dataLoaderRepository = new DataLoaderRepository(new ObjectMapper());
        this.fireStationRepository = new FireStationRepository(new ArrayList<>(this.dataLoaderRepository.getResponse().getFirestations()));
        this.personRepository = new PersonRepository(new ArrayList<>(this.dataLoaderRepository.getResponse().getPersons()));
        this.medicalRecordRepository = new MedicalRecordRepository(new ArrayList<>(this.dataLoaderRepository.getResponse().getMedicalrecords()));
    }


    @Override
    public List<FireStation> getAllFireStations() {
        return this.fireStationRepository.getAllFireStations();
    }

    @Override
    public FireStation getFireStation(Integer station) {
        return this.fireStationRepository.getFireStation(station);
    }

    @Override
    public FireStation saveFireStation(FireStation fireStation) {
        if(getFireStation(fireStation.getStation()) != null){
            log.error("404, entity not found");
        }
        return this.fireStationRepository.saveFireStation(fireStation);
    }

    @Override
    public Boolean deleteFireStation(Integer station) {
        FireStation fireStation = getFireStation(station);
        if (fireStation == null ){
            return false;
        }
        this.fireStationRepository.deleteFireStation(fireStation);
        return true;
    }
    // TODO finish updateServiceStation
    @Override
    public FireStation updateFireStation(FireStation fireStation) {
        return null;
    }

    @Override
    public Map<List<String>, List<PersonStation>> getPersonInfoByStation(int station) {
        List<Person> persons = this.personRepository.getPersons();
        FireStation fireStations = this.fireStationRepository.getFireStation(station);

        Map<List<String>, List<PersonStation>> personInfoByStationPlusCounter = new HashMap<>();

        List<PersonStation> personStations = new ArrayList<>();
        List<String> personCounter = new ArrayList<>();

        int adult = 0;
        int child = 0;

        for(Person person : persons){

                int stationPerson = getStationNumberPerson(person.getAddress());
                MedicalRecord medicalRecord = this.medicalRecordRepository.getMedicalRecordsByFirstNameAndLastName(person.getFirstName(), person.getLastName());
                String birthdate = this.medicalRecordRepository.getMedicalRecordsByFirstNameAndLastName(person.getFirstName(), person.getLastName()).getBirthdate();
                int age = this.medicalRecordRepository.calculateAgeFromBirthdate(birthdate);


            if(stationPerson == fireStations.getStation()){

            PersonStation personStation = new PersonStation();
                personStation.setFirstName(person.getFirstName());
                personStation.setAddress(person.getAddress());
                personStation.setLastName(person.getLastName());
                personStation.setPhone(person.getPhone());
                personStations.add(personStation);
                if(age > 18){
                    adult++;
                }
                if(age < 18){
                    child++;
                }
            }
        }
        personCounter.add("Child :"+child);
        personCounter.add("Adult :"+adult);

        personInfoByStationPlusCounter.put(personCounter,personStations);
        return personInfoByStationPlusCounter;
    }

    @Override
    public int getStationNumberPerson(String address){
        List<Person> persons = this.personRepository.getPersonsByAddress(address);

        for(Person person : persons){

            FireStation fireStation = this.fireStationRepository.getFireStationByAddress(address);
              if (Objects.equals(person.getAddress(), fireStation.getAddress())){
                  return fireStation.getStation();
              }
            }
        return 0;
        }

    @Override
    public List<String> getPhoneAlert (int fireStationNumber) {
        List<Person> persons = this.personRepository.getPersons();
        FireStation fireStations = this.fireStationRepository.getFireStation(fireStationNumber);

        List<String> phoneList = new ArrayList<>();

        for(Person person : persons){
            int stationPerson = getStationNumberPerson(person.getAddress());

            if(stationPerson == fireStations.getStation()){
                phoneList.add(person.getPhone());
            }
        }

        return phoneList;
    }
}
