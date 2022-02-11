package com.safetynetalerts.webapp.controller;

import com.safetynetalerts.webapp.model.FireStation;
import com.safetynetalerts.webapp.service.FireStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class FireStationController {

    @Autowired
    private FireStationService fireStationService;

    //http://localhost:8080/firestation
    @GetMapping("/firestation")
        public List<FireStation> getAllFireStations() throws IOException {
            return fireStationService.getAllFireStations();
        }
    }