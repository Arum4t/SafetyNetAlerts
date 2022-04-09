package com.safetynetalerts.webapp.Data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynetalerts.webapp.model.*;
import com.safetynetalerts.webapp.model.specific.*;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PersonData {

    static ObjectMapper objectMapper = new ObjectMapper();

    public static JsonModel getJsonModel() throws IOException {
        return objectMapper.readValue(new File("src/test/java/com/safetynetalerts/webapp/Data/json_list_test.json"), JsonModel.class);
    }

    public static Person onePerson() {

        Person person = new Person();
        person.setLastName("Zemicks");
        person.setAddress("892 Downing Ct");
        person.setEmail("soph@email.com");
        person.setFirstName("Sophia");
        person.setPhone("841-874-7878");
        person.setZip("97451");
        person.setCity("Culver");

        return person;
    }

    public static List<Person> family() {

        List<Person> personList = new ArrayList<>();
        Person person = new Person();
        person.setLastName("Boyd");
        person.setAddress("1509 Culver St");
        person.setEmail("jaboyd1@email.com");
        person.setFirstName("John");
        person.setPhone("841-874-6512");
        person.setZip("97451");
        person.setCity("Culver");
        personList.add(person);
        Person person2 = new Person();
        person2.setLastName("Boyd");
        person2.setAddress("1509 Culver St");
        person2.setEmail("drk1@email.com");
        person2.setFirstName("Jacob");
        person2.setPhone("841-874-6513");
        person2.setZip("97451");
        person2.setCity("Culver");
        personList.add(person2);
        Person person3 = new Person();
        person3.setLastName("Boyd");
        person3.setAddress("1509 Culver St");
        person3.setEmail("tenz1@email.com");
        person3.setFirstName("Tenley");
        person3.setPhone("841-874-6512");
        person3.setZip("97451");
        person3.setCity("Culver");
        personList.add(person3);
        Person person4 = new Person();
        person4.setLastName("Boyd");
        person4.setAddress("1509 Culver St");
        person4.setEmail("jaboyd2@email.com");
        person4.setFirstName("Roger");
        person4.setPhone("841-874-6512");
        person4.setZip("97451");
        person4.setCity("Culver");
        personList.add(person4);
        Person person5 = new Person();
        person5.setLastName("Boyd");
        person5.setAddress("1509 Culver St");
        person5.setEmail("jaboyd3@email.com");
        person5.setFirstName("Felicia");
        person5.setPhone("841-874-6544");
        person5.setZip("97451");
        person5.setCity("Culver");
        personList.add(person5);
        return personList;
    }

    public static Person createPerson() {
        Person person = new Person();
        person.setLastName("Schmidt");
        person.setAddress("22 rue des lions");
        person.setEmail("albert-s@email.com");
        person.setFirstName("Albert");
        person.setPhone("842-872-6222");
        person.setZip("67000");
        person.setCity("Strasbourg");
        return person;
    }

    public static Person personCityUpdate() {
        Person person = new Person();
        person.setLastName("Carman");
        person.setAddress("834 Binoc Ave");
        person.setEmail("tenz2@email.com");
        person.setFirstName("Tessa");
        person.setPhone("841-874-6512");
        person.setZip("97451");
        person.setCity("Lille");

        return person;
    }

    public static List<String> getEmailByCity() {
        List<String> emailList = new ArrayList<>();
        emailList.add("jaboyd1@email.com");
        emailList.add("drk1@email.com");
        emailList.add("tenz1@email.com");
        emailList.add("jaboyd2@email.com");
        emailList.add("jaboyd3@email.com");
        emailList.add("drk2@email.com");
        emailList.add("tenz2@email.com");
        emailList.add("jaboyd4@email.com");
        emailList.add("jaboyd5@email.com");
        emailList.add("tcoop@ymail.com");
        emailList.add("lily@email.com");
        emailList.add("soph@email.com");
        emailList.add("ward@email.com");
        emailList.add("zarc@email.com");
        emailList.add("reg@email.com");
        emailList.add("jpeter1@email.com");
        emailList.add("jpeter2@email.com");
        emailList.add("aly@imail.com");
        emailList.add("bstel1@email.com");
        emailList.add("ssanw@email.com");
        emailList.add("bstel2@email.com");
        emailList.add("clivfd@ymail.com");
        emailList.add("gramps@email.com");
        return emailList;
    }

    public static Map<String, List<ChildAlert>> listChildAlert() {
        Map<String, List<ChildAlert>> childList = new HashMap<>();
        String name = "Boyd";
        List<ChildAlert> childAlerts = new ArrayList<>();

        ChildAlert child = new ChildAlert();
        child.setFirstName("Tenley");
        child.setLastName("Boyd");
        child.setAge(10);
        childAlerts.add(child);

        ChildAlert child2 = new ChildAlert();
        child2.setFirstName("Roger");
        child2.setLastName("Boyd");
        child2.setAge(4);
        childAlerts.add(child2);

        childList.put(name, childAlerts);
        return childList;
    }

    public static List<PersonAllInfo> getPersonAllInfo() throws IOException {
        return getJsonModel().getFullInfo();
    }

    public static List<PersonFireStationResponse> getPersonsInfoByStation() throws IOException {
        return getJsonModel().getPersonInfoByFireStation();
    }

    public static List<String> getPhoneAlert() throws IOException {
        return getJsonModel().getPhoneAlert();
    }

    public static List<PersonFireZoneResponse> getFireZone() throws IOException {
        return getJsonModel().getFireZones();
    }

    public static Map<String, List<PersonInfoByFloodZone>> getFloodZone() throws IOException {
        return getJsonModel().getFloodZones();
    }

    public static List<Person> personsByFireStation() throws IOException {
        return getJsonModel().getPersonsByFireStation();
    }

    public static List<Person> allPerson() throws IOException {
        return getJsonModel().getAllPerson();
    }

}