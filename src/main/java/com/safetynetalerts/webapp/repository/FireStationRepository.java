package com.safetynetalerts.webapp.repository;

import com.safetynetalerts.webapp.model.FireStation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class FireStationRepository implements IFireStationRepository {

    private static final Logger logger = LoggerFactory.getLogger(FireStationRepository.class);

    private ArrayList<FireStation> fireStations;

    public FireStationRepository(ArrayList<FireStation> fireStations) {
        if (this.fireStations == null){
            this.fireStations = new ArrayList<>();
        }
        this.fireStations.addAll(fireStations);
    }

    @Override
    public List<FireStation> getAll(){
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

    @Override
    public FireStation saveFireStation(FireStation fireStation) {
        this.fireStations.add(fireStation);
        return fireStation;
    }

    @Override
    public FireStation deleteFireStation(FireStation fireStation) {
        this.fireStations.remove(fireStation);
        return fireStation;
    }


    @Override
    public FireStation updateFireStation(FireStation fireStation) {

        this.fireStations.set(getAll().indexOf(getFireStation(fireStation.getStation())), fireStation);
        return fireStation;
    }

    @Override
    public FireStation getFireStationByAddress(String address) {
        for(FireStation fireStation : this.fireStations){
            if(Objects.equals(fireStation.getAddress(), address)){
                logger.info("Request get firestation address successful");
                return fireStation;
            }
        }
        logger.info("Request get firestation address failed");
        return null;
    }

}
