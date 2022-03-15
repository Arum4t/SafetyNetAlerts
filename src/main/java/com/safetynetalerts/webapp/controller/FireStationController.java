package com.safetynetalerts.webapp.controller;

import com.safetynetalerts.webapp.model.*;
import com.safetynetalerts.webapp.service.FireStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class FireStationController {

    @Autowired
    private FireStationService fireStationService;

    //http://localhost:8080/fireStations
    @GetMapping("/fireStations")
    public List<FireStation> getAllFireStations() {
        return fireStationService.getAllFireStations();
    }

    @PostMapping("/fireStations")
    public FireStation saveFireStation(@RequestBody FireStation fireStation) {
        return this.fireStationService.saveFireStation(fireStation);
    }

    @PutMapping("/fireStations")
    public FireStation updateFireStation (@RequestBody FireStation fireStation) {
        return this.fireStationService.updateFireStation(fireStation);
    }

    @DeleteMapping("/fireStations/{station}")
    public String deleteFireStation (@PathVariable("station") Integer station) {
        Boolean ok = fireStationService.deleteFireStation(station);
        if (ok) {
            return "ok";
        }
        return "not ok";
    }
    //http://localhost:8080/fireStations?stationNumber=2
    @GetMapping(value = "/fireStations", params = "stationNumber")
    public Map<List<String>, List<PersonStation>> getStationNumberPerson (@RequestParam int stationNumber){
        return fireStationService.getPersonInfoByStation(stationNumber);
    }
    //http://localhost:8080/phoneAlert?fireStation=2
    @GetMapping(value = "/phoneAlert", params = "fireStation")
    public List<String> getPhoneAlert (@RequestParam int fireStation){
        return fireStationService.getPhoneAlert(fireStation);
    }
    //http://localhost:8080/fire?address=1509 Culver St
    @GetMapping("fire")
    public Map<String, List<FireZone>> getFireZone(@RequestParam String address){
        return fireStationService.getFireZone(address);
    }

    //http://localhost:8080/flood/stations?stations=1,2,3
    @GetMapping(value = "flood/stations", params = "stations")
    public Map<String, List<FloodZone>> getFloodZone(@RequestParam List<Integer> stations){
        return fireStationService.getFloodZone(stations);
    }
}