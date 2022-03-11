package com.safetynetalerts.webapp.service;

import com.safetynetalerts.webapp.model.FireStation;
import com.safetynetalerts.webapp.model.Person;
import com.safetynetalerts.webapp.model.PersonStation;

import java.util.List;
import java.util.Map;

public interface IFireStationService {

    List<FireStation> getAllFireStations();

    FireStation getFireStation(Integer station);

    FireStation saveFireStation(FireStation fireStation);

    Boolean deleteFireStation(Integer station);

    FireStation updateFireStation(FireStation fireStation);

    Map<List<String>, List<PersonStation>> getPersonInfoByStation(int station);

    int getStationNumberPerson(String address);

    List<String> getPhoneAlert (int fireStationNumber);

    List<FireZone> getFireZone (String address);

}
