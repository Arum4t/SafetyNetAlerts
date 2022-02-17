package com.safetynetalerts.webapp.repository;

import com.safetynetalerts.webapp.model.FireStation;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class FireStationRepository implements IFireStationRepository {

    private ArrayList<FireStation> fireStations;

    public FireStationRepository(ArrayList<FireStation> fireStations) {
        this.fireStations = fireStations;
    }

    @Override
    public List<FireStation> getFireStations(){
        return this.fireStations;
    }

    @Override
    public FireStation getFireStation(Integer station) {
        for(FireStation fireStation : this.fireStations){
            if (Objects.equals(fireStation.getStation(),station)) {
                return fireStation;
            }
        }

        return null;
    }
}
