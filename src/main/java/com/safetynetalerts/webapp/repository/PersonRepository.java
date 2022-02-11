package com.safetynetalerts.webapp.repository;

import com.safetynetalerts.webapp.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepository {


    private ArrayList<Person> persons;

    public PersonRepository(ArrayList<Person> persons) {
        this.persons = persons;
    }

    public List<Person> getPersons(){
        return this.persons;
    }

/*


    private File file;
    private Person[] persons;

    public DataAccessPersonImpl() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        DataLoader data = mapper.readValue(new File("src/main/resources/json_list.json"), DataLoader.class);
        this.persons = data.getPersons();
        System.out.println(this.persons);

    }*/
}