package com.safetynetalerts.webapp.service;

import com.safetynetalerts.webapp.model.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IFireStationService {

    List<FireStation> getAll();

    FireStation getFireStation(Integer station);

    FireStation saveFireStation(FireStation fireStation);

    Boolean deleteFireStation(Integer station);

    FireStation updateFireStation(FireStation fireStation);

    int getStationNumberPerson(String address);

    List<String> getPhoneAlert (int fireStationNumber);

    Map<String, List<FireZone>> getFireZone (String address) throws IOException;

    Map<String, List<FloodZone>> getFloodZone(List<Integer> stations) throws IOException;

}
