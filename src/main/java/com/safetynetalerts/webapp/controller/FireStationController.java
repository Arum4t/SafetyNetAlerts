package com.safetynetalerts.webapp.controller;

import com.safetynetalerts.webapp.model.FireStation;
import com.safetynetalerts.webapp.model.Person;
import com.safetynetalerts.webapp.model.PersonStation;
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
    @GetMapping("/fireStation")
    public List<FireStation> getAllFireStations() {
        return fireStationService.getAllFireStations();
    }

    // http://localhost:8080/fireStation?station=<>
    @GetMapping(value = "/fireStations", params = "station")
    public FireStation getStation(@RequestParam Integer station) {
        return this.fireStationService.getFireStation(station);
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
}