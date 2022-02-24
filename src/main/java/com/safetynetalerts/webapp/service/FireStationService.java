package com.safetynetalerts.webapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynetalerts.webapp.model.FireStation;
import com.safetynetalerts.webapp.repository.DataLoaderRepository;
import com.safetynetalerts.webapp.repository.FireStationRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class FireStationService implements IFireStationService {

    private DataLoaderRepository dataLoaderRepository;
    private FireStationRepository fireStationRepository;
    private ArrayList<FireStation> stations;

    public FireStationService() throws IOException {
        this.dataLoaderRepository = new DataLoaderRepository(new ObjectMapper());
        if(this.stations == null){
            this.stations = new ArrayList<>();
        }
        this.stations.addAll(new ArrayList<>(this.dataLoaderRepository.getResponse().getFirestations()));
        this.fireStationRepository = new FireStationRepository(this.stations);
    }


    @Override
    public List<FireStation> getAllFireStations() {
        return this.fireStationRepository.getFireStations();
    }

    @Override
    public FireStation getFireStation(Integer station) {
        return this.fireStationRepository.getFireStation(station);
    }

    @Override
    public FireStation saveFireStation(FireStation fireStation) {
        return null;
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
        return null;
    }
}
