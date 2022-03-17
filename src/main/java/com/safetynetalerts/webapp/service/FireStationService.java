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
        if(this.fireStationRepository.getFireStationByAddress(fireStation.getAddress()) != null){
            log.info("Request successful");
            return this.fireStationRepository.updateFireStation(fireStation);
        } log.error("FireStation not found");
        return null;
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

    @Override
    public Map<String, List<FireZone>> getFireZone(String address) throws IOException {
        List<Person> persons = this.personRepository.getPersonsByAddress(address);
        FireStation fireStations = this.fireStationRepository.getFireStationByAddress(address);

        Map<String, List<FireZone>> fireZones = new HashMap<>();

        for(Person person : persons){

            String station = "Station :" + fireStations.getStation();

            int stationPerson = getStationNumberPerson(person.getAddress());
            MedicalRecord medicalRecord = this.medicalRecordRepository.getMedicalRecordsByFirstNameAndLastName(person.getFirstName(), person.getLastName());
            String birthdate = this.medicalRecordRepository.getMedicalRecordsByFirstNameAndLastName(person.getFirstName(), person.getLastName()).getBirthdate();
            MedicalRecordService calculateAge = new MedicalRecordService();
            int age = calculateAge.calculateAgeFromBirthdate(birthdate);

            if(stationPerson == fireStations.getStation()){
                List<FireZone> stationList = fireZones.get(station);
                if(stationList  != null){

                    FireZone fireZone = new FireZone();
                    fireZone.setAge(age);
                    fireZone.setPhone(person.getPhone());
                    fireZone.setLastName(person.getLastName());
                    fireZone.setAllergies(medicalRecord.getAllergies());
                    fireZone.setMedications(medicalRecord.getMedications());
                    stationList.add(fireZone);
                    continue;
                }
                stationList = new ArrayList<>();
                FireZone fireZone = new FireZone();
                fireZone.setAge(age);
                fireZone.setPhone(person.getPhone());
                fireZone.setLastName(person.getLastName());
                fireZone.setAllergies(medicalRecord.getAllergies());
                fireZone.setMedications(medicalRecord.getMedications());
                stationList.add(fireZone);
                fireZones.putIfAbsent(station, stationList);

            }
        }

        return fireZones;
    }

    @Override
    public Map<String, List<FloodZone>> getFloodZone(List<Integer> stations) throws IOException {
        List<Person> persons = this.personRepository.getPersons();

        Map<String, List<FloodZone>> floodZones = new HashMap<>();


            for(Person person : persons) {

                String address = "Address : " + person.getAddress();

                int stationPerson = getStationNumberPerson(person.getAddress());
                MedicalRecord medicalRecord = this.medicalRecordRepository.getMedicalRecordsByFirstNameAndLastName(person.getFirstName(), person.getLastName());
                String birthdate = this.medicalRecordRepository.getMedicalRecordsByFirstNameAndLastName(person.getFirstName(), person.getLastName()).getBirthdate();
                MedicalRecordService calculateAge = new MedicalRecordService();
                int age = calculateAge.calculateAgeFromBirthdate(birthdate);

                if (stations.contains(stationPerson)) {
                    List<FloodZone> addressList = floodZones.get(address);
                    if(addressList != null){
                        FloodZone floodZone = new FloodZone();
                        floodZone.setPhone(person.getPhone());
                        floodZone.setAge(age);
                        floodZone.setLastName(person.getLastName());
                        floodZone.setPhone(person.getPhone());
                        floodZone.setAllergies(medicalRecord.getAllergies());
                        floodZone.setMedications(medicalRecord.getMedications());
                        addressList.add(floodZone);
                        continue;
                    }
                    addressList = new ArrayList<>();
                    FloodZone floodZone = new FloodZone();
                    floodZone.setPhone(person.getPhone());
                    floodZone.setAge(age);
                    floodZone.setLastName(person.getLastName());
                    floodZone.setPhone(person.getPhone());
                    floodZone.setAllergies(medicalRecord.getAllergies());
                    floodZone.setMedications(medicalRecord.getMedications());
                    addressList.add(floodZone);
                    floodZones.putIfAbsent(address, addressList);
                }

            }

        return floodZones;
    }

}

