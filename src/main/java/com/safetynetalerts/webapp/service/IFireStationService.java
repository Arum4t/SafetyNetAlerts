package com.safetynetalerts.webapp.service;

import com.safetynetalerts.webapp.model.*;

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

    Map<String, List<FireZone>> getFireZone (String address);

    //Map<String, List<FloodZone>> getFloodZone(List<Integer> stations);

}
