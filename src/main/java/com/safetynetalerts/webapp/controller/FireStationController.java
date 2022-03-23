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
        return fireStationService.getAll();
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

}