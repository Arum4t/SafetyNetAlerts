package com.safetynetalerts.webapp.service;

import com.safetynetalerts.webapp.model.FireStation;

import java.util.List;

public interface IFireStationService {

    List<FireStation> getAllFireStations();

    FireStation getFireStation(Integer station);

    FireStation saveFireStation(FireStation fireStation);

    Boolean deleteFireStation(Integer station);

    FireStation updateFireStation(FireStation fireStation);


}