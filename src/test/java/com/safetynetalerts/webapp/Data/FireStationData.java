package com.safetynetalerts.webapp.Data;

import com.safetynetalerts.webapp.model.FireStation;
import lombok.Data;

import java.io.IOException;
import java.util.List;

import static com.safetynetalerts.webapp.Data.PersonData.getJsonModel;


@Data
public class FireStationData {


    public static FireStation getFireStation() {
        FireStation fireStation = new FireStation();
        fireStation.setStation(1);
        fireStation.setAddress("644 Gershwin Cir");
      return fireStation;
    }


    public static FireStation saveFireStation() {
        FireStation fireStation = new FireStation();
        fireStation.setStation(11);
        fireStation.setAddress("82 rue de la digue");
        return fireStation;
    }

    public static FireStation updateFireStationAddress() {
        FireStation fireStation = new FireStation();
        fireStation.setStation(1);
        fireStation.setAddress("644 Gershwin");
        return fireStation;
    }
    public static List<FireStation> allFireStation() throws IOException {
        return getJsonModel().getAllFireStation();
    }
}
