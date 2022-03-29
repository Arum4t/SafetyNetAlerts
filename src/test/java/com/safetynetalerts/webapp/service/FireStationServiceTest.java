package com.safetynetalerts.webapp.service;

import com.safetynetalerts.webapp.Data.FireStationData;
import com.safetynetalerts.webapp.Data.PersonData;
import com.safetynetalerts.webapp.model.FireStation;
import com.safetynetalerts.webapp.repository.DataLoaderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class FireStationServiceTest {

    @Autowired
    private DataLoaderRepository dataLoaderRepository;
    @Autowired
    private FireStationService fireStationService;

    @BeforeEach
    public void setUp() throws IOException {
        FireStationData fireStationData = new FireStationData();
        fireStationService = new FireStationService();

    }
    @Test
    public void getFireStationTest(){
        FireStation fireStation = FireStationData.getFireStation();
        int station = 1;
        FireStation fireStation1 = fireStationService.getFireStation(station);
        Assertions.assertEquals(fireStation, fireStation1);
    }
    @Test
    public void saveFireStationTest(){
    //créer une station qui n'éxiste pas.
        FireStation createFireStation = FireStationData.saveFireStation();
        FireStation current = fireStationService.saveFireStation(createFireStation);
        Assertions.assertNotNull(current);
    }
    @Test
    public void deleteFireStationTest(){
        int station = 1;
        Boolean current = fireStationService.deleteFireStation(station);
        Assertions.assertTrue(current);
    }
    @Test
    public void updateFireStationTest(){
    // "644 Gershwin" au lieu de "644 Gershwin Cir"
    FireStation fireStation = FireStationData.updateFireStationAddress();
    FireStation current = fireStationService.updateFireStation(fireStation);
    System.out.println(current);
    System.out.println(fireStation);
    Assertions.assertEquals("644 Gershwin", current.getAddress());
    }

    @Test
    public void getStationNumberByPersonAddress(){
    String address = "489 Manchester St";
    int current = fireStationService.getStationNumberByPersonAddress(address);
    Assertions.assertEquals(4, current);
    }
}
