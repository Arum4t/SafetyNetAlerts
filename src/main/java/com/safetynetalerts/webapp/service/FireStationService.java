package com.safetynetalerts.webapp.service;

import com.safetynetalerts.webapp.model.FireStation;
import com.safetynetalerts.webapp.repository.FireStationRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Data
@Service
public class FireStationService {

    @Autowired
    private FireStationRepository fireStationRepository;

    public List<FireStation> getAllFireStations() throws IOException {
        return fireStationRepository.getFireStations();
    }
}
