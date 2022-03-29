package com.safetynetalerts.webapp.service;

import com.safetynetalerts.webapp.model.DTO.ChildAlert;
import com.safetynetalerts.webapp.model.DTO.PersonAllInfo;
import com.safetynetalerts.webapp.model.DTO.PersonInfoByFloodZone;
import com.safetynetalerts.webapp.model.Person;
import com.safetynetalerts.webapp.model.PersonFireStationResponse;
import com.safetynetalerts.webapp.model.PersonFireZoneResponse;
import com.safetynetalerts.webapp.repository.DataLoaderRepository;
import com.safetynetalerts.webapp.Data.PersonData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



@SpringBootTest
public class PersonServiceTest {

    @Autowired
    private DataLoaderRepository dataLoaderRepository;

    @Autowired
    private PersonService personService;

    @BeforeEach
    public void setUp() throws IOException {
        PersonData personData = new PersonData();
        personService = new PersonService();

    }

    @Test
    public void deletePersonTest(){
        //True = delete , False = la personne n'éxiste pas.
    String firstName = "Warren";
    String lastName = "Zemicks";
    Boolean current = personService.deletePerson(firstName, lastName);
    Assertions.assertTrue(current);
    }
    @Test
    public void updatePersonTest() {
        // City : Culver / Changer en Lille. Changement attendu avec la méthode update
        Person person = PersonData.personCityUpdate();

        Person current = personService.updatePerson(person);

        Assertions.assertEquals("Lille", current.getCity());
    }

    @Test
    public void getPersonTest() {
        //La personne créer == la personne récupérer par la méthode via son email
        Person getPerson = PersonData.onePerson();
        String email = "soph@email.com";

        Person current = personService.getPerson(email);

        Assertions.assertEquals(getPerson, current);
    }
    @Test
    public void SavePersonTest() {
        //Personne qui n'existe pas dans le fichier Json. Si elle existe current == null.
        Person createPerson = PersonData.createPerson();

        Person current = personService.savePerson(createPerson);

        Assertions.assertNotNull(current);
    }
    @Test
    public void getPersonsByAddressTest() {
        List<Person> personList = PersonData.family();
        String address = "1509 Culver St";

        List<Person> current = personService.getPersonsByAddress(address);
        Assertions.assertEquals(personList, current);

    }
    @Test
    public void getEmailByCityTest(){
    List<String> emailList = PersonData.getEmailByCity();
    String city = "Culver";

    List<String> current = personService.getEmailByCity(city);
    Assertions.assertEquals(emailList, current);
    }
    @Test
    public void listChildAlertTest() throws IOException {
        Map<String, List<ChildAlert>> childList = PersonData.listChildAlert();
        String address = "1509 Culver St";
        Map<String, List<ChildAlert>> current = personService.listChildAlert(address);
        Assertions.assertEquals(childList, current);

    }
    @Test
    public void getPersonsInfoByStationTest() throws IOException{
        List<PersonFireStationResponse> personFireStationResponses = PersonData.getPersonsInfoByStation();

        int stationNumber =2;
        List<PersonFireStationResponse> current = personService.getPersonsInfoByStation(stationNumber);

        Assertions.assertEquals(personFireStationResponses,current);
    }
    @Test
    public void getPhoneAlertTest() throws IOException{
    List<String> listPhone = PersonData.getPhoneAlert();

    int fireStation = 2;
    List<String> current = personService.getPhoneAlert(fireStation);
    Assertions.assertEquals(listPhone,current);
    }
    @Test
    public void getFireZoneTest() throws IOException{
    List <PersonFireZoneResponse> fireZoneResponses = PersonData.getFireZone();

    String address = "1509 Culver St";
    List<PersonFireZoneResponse> current = personService.getFireZone(address);
    Assertions.assertEquals(fireZoneResponses, current);
    }

   @Test
    public void getFloodZoneTest() throws IOException{
        Map<String, List<PersonInfoByFloodZone>> floodZone = PersonData.getFloodZone();
        List<Integer> stations = new ArrayList<>();
        stations.add(1);
        stations.add(2);
        Map<String, List<PersonInfoByFloodZone>> current = personService.getFloodZone(stations);
        Assertions.assertEquals(floodZone, current);

    }
    @Test
    public void getPersonAllInfoTest() throws IOException{
        List<PersonAllInfo> personAllInfos = PersonData.getPersonAllInfo();

        String lastName = "Boyd";
        String firstName = "John";
        List<PersonAllInfo> current = personService.getPersonAllInfo(firstName, lastName);
        Assertions.assertEquals(personAllInfos ,current);
    }
    @Test
    public void getPersonByStationTest() throws IOException {
    List<Person> personList = PersonData.personsByFireStation();
    int station = 1;
    List<Person> current = personService.getPersonByFireStation(station);
    Assertions.assertEquals(personList, current);
    }
}
