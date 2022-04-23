## Projet safetynetalerts
Hi, I'm Quentin Schnurr
I’m currently working on safetynetalerts.
It's a local web API, reading a specific json files to process informations using Endpoints. 
This API respects SOLID princips and REST architecture.

---------------------------------------

##  Run API

Fork and clone the project.

>The following are command you can use in your IDE or command prompt.

Load Maven dependencies `maven install`.

Generate jar file `mvn clean package`.

The jar file is in `target/`.  

Target the file `cd yourPathOfJarFile`.

Run `java -jar yourJarFile.jar`.

Default Controller URL `http://localhost:8080/`.   

---------------------------------------

## Json file
> example  of the json_list.json : 

``` 
{
  "persons": [
    {
      "firstName": "John",
      "lastName": "Boyd",
      "address": "1509 Culver St",
      "city": "Culver",
      "zip": "97451",
      "phone": "841-874-6512",
      "email": "jaboyd1@email.com"
    },
    {
      "firstName": "Jacob",
      "lastName": "Boyd",
      "address": "1509 Culver St",
      "city": "Culver",
      "zip": "97451",
      "phone": "841-874-6513",
      "email": "drk1@email.com"
    },
    {
      "firstName": "Jonanathan",
      "lastName": "Marrack",
      "address": "29 15th St",
      "city": "Culver",
      "zip": "97451",
      "phone": "841-874-6513",
      "email": "drk2@email.com"
    }
  ],
  "firestations": [
    {
      "address": "1509 Culver St",
      "station": "3"
    },
    {
      "address": "29 15th St",
      "station": "2"
    }
  ],
  "medicalrecords": [
    {
      "firstName": "John",
      "lastName": "Boyd",
      "birthdate": "03/06/1984",
      "medications": [
        "aznol:350mg",
        "hydrapermazol:100mg"
      ],
      "allergies": [
        "nillacilan"
      ]
    },
    {
      "firstName": "Tenley",
      "lastName": "Boyd",
      "birthdate": "02/18/2012",
      "medications": [

      ],
      "allergies": [
        "peanut"
      ]
    }
  ]
}
```  
---------------------------------------  
  
## Endpoints 

**http://localhost:8080/fireStations?stationNumber={station_number}**
  
>This url should return a list of people covered by the corresponding fire station.
>So if the station number = 1, it must return the inhabitants covered by station number 1. The list
>must include the following specific information: first name, last name, address, telephone number. Furthermore,
>it must provide a count of the number of adults and the number of children (any individual aged 18 or over
>less) in the service area.  
  
**http://localhost:8080/childAlert?address={address}**
  
>This url should return a list of children (any individual aged 18 or younger) living at this address.
>The list should include the first and last name of each child, their age and a list of others
>household members. If there is no child, this url may return an empty string
  
**http://localhost:8080/phoneAlert?firestation={firestation_number}**
  
>This url must return a list of the telephone numbers of the residents served by the fire station.
>firefighters. We will use it to send emergency text messages to specific households.
  
**http://localhost:8080/fire?address={address}**
  
>This url must return the list of inhabitants living at the given address as well as the number of the barracks
>of firefighters serving it. The list should include name, phone number, age and background
>medical (drugs, dosage and allergies) of each person
  
**http://localhost:8080/flood/stations?stations={list_of_station_numbers}**
  
>This url should return a list of all homes served by the barracks. This list should include the
>people by address. It must also include the name, telephone number and age of the inhabitants, and
>include their medical history (medications, dosage and allergies) next to each name.
  
**http://localhost:8080/personInfo?firstName={firstName(not_required)}&lastName={lastName}**
  
>This url must return the name, address, age, email address and medical history (drugs,
>dosage, allergies) of each inhabitant. If more than one person has the same name, they must
>all appear.
  
**http://localhost:8080/communityEmail?city={city}**
  
>This url must return the email addresses of all the inhabitants of the city


## READ/GET

**/persons**

**/fireStations**

**/medicalRecords**

To get all persons, fireStations or medicalRecords.


## CREATE / UPDATE / DELETE

**/persons**
```
{ "firstName":"firstName", "lastName":"lastName", "address":"address", "city":"city", "zip":"000000", "phone":"0000000", "email":"email@email.com" }
```  

>To create and modify you need a Json body.


**/persons/{firstName}/{lastName}**

>To delete a person.


**/fireStations**
```  
{ "address":"address", "station":"0" }
```  

>To create and modify you need a Json body.


**/fireStations/{station}**

>To delete a fireStation.


**/medicalRecords**
```  
{ "firstName":"firstName", "lastName":"lastName", "birthdate":"jj/mm/yyyy", "medications":["medications", "medications"], "allergies":["allergies"] }
```  

>To create and modify you need a Json body.


**/medicalRecords/{firstName}/{lastName}**

>To delete a medicalRecord.

  
---------------------------------------


## Languages and Tools
<p align="left"> <a href="https://www.java.com" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="java" width="40" height="40"/> </a> <a href="https://postman.com" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/getpostman/getpostman-icon.svg" alt="postman" width="40" height="40"/> </a> <a href="https://spring.io/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg" alt="spring" width="40" height="40"/> </a> </p>
