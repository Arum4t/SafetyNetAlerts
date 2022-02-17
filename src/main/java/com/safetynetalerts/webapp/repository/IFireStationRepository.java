package com.safetynetalerts.webapp.repository;

import com.safetynetalerts.webapp.model.FireStation;

import java.util.List;

public interface IFireStationRepository {

    List<FireStation> getFireStations();

    FireStation getFireStation(Integer station);
}
