package com.safetynetalerts.webapp.controller;

import com.safetynetalerts.webapp.model.FireStation;
import com.safetynetalerts.webapp.model.Person;
import com.safetynetalerts.webapp.service.FireStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class FireStationController {

    @Autowired
    private FireStationService fireStationService;

    //http://localhost:8080/firestation
    @GetMapping("/firestations")
    public List<FireStation> getAllFireStations() {
        return fireStationService.getAllFireStations();
    }

    // http://localhost:8080/firestation?station=<>
    @GetMapping("/firestations")
    public FireStation getStation(@RequestParam Integer station) {
        return this.fireStationService.getFireStation(station);
    }

    @PostMapping("/firestations")
    public FireStation saveFireStation(@RequestBody FireStation fireStation) {
        return this.fireStationService.saveFireStation(fireStation);
    }

    @PutMapping("/firestations")
    public FireStation updateFireStation (FireStation fireStation) {
        return this.fireStationService.updateFireStation(fireStation);
    }

    @DeleteMapping("/firestations")
    public String deleteFireStation (@PathVariable("station") Integer station) {
        Boolean ok = fireStationService.deleteFireStation(station);
        if (ok) {
            return "ok";
        }
        return "not ok";
    }
}