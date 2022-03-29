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
    public List<FireStation> getAll() {
        return this.fireStationRepository.getAll();
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

    @Override
    public FireStation updateFireStation(FireStation fireStation) {
        if(getFireStation(fireStation.getStation()) != null){
            log.info("Request successful");
            return this.fireStationRepository.updateFireStation(fireStation);
        } log.error("FireStation not found");
        return null;
    }

    @Override
    public int getStationNumberByPersonAddress(String address){
        List<Person> persons = this.personRepository.getPersonsByAddress(address);

        for(Person person : persons){

            FireStation fireStation = this.fireStationRepository.getFireStationByAddress(address);
              if (Objects.equals(person.getAddress(), fireStation.getAddress())){
                  return fireStation.getStation();
              }
            }
        return 0;
        }

}

