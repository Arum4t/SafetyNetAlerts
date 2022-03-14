package com.safetynetalerts.webapp.repository;

import com.safetynetalerts.webapp.model.FireStation;

import java.util.List;

public interface IFireStationRepository {

    List<FireStation> getAllFireStations();

    FireStation getFireStation(Integer station);

    FireStation saveFireStation(FireStation fireStation);

    FireStation deleteFireStation(FireStation fireStation);

    FireStation updateFireStation(FireStation fireStation);

    FireStation getFireStationByAddress(String address);



}
