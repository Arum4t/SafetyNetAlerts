package com.safetynetalerts.webapp.repository;

import com.safetynetalerts.webapp.model.FireStation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
@Data
public class FireStationRepository {

    @Autowired
    private DataLoaderRepository dataLoaderRepository;

    public List<FireStation> getFireStations() throws IOException {
        return null;
        //new ArrayList<>(dataLoaderRepository.dataAccess().getFirestations());
    }
}
