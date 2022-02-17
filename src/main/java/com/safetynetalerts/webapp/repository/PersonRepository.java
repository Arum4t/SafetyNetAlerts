package com.safetynetalerts.webapp.repository;

import com.safetynetalerts.webapp.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class PersonRepository implements IPersonRepository{


    private ArrayList<Person> persons;

    public PersonRepository(ArrayList<Person> persons) {
        if(this.persons == null){
            this.persons = new ArrayList<>();
        }
        this.persons.addAll(persons);
    }

    @Override
    public List<Person> getPersons(){
        return this.persons;
    }

    @Override
    public Person getPerson(String email) {
        for (Person person : this.persons) {
            if(Objects.equals(person.getEmail(), email)){
                return person;
            }
        }
        return null;
    }

    @Override
    public Person savePerson(Person person) {
        this.persons.add(person);
        return person;
    }

    @Override
    public Person updatePerson(String lastName, String firstName) {
        for (Person person : this.persons){
            // Si le nom et le prénom sont les mêmes
            if ((Objects.equals(person.getLastName(), lastName))&&((Objects.equals(person.getFirstName(), firstName)))){
                return person;
            } else {


                //Set les nouvelles valeurs de cette personne


            }
        }
        return null;
    }

    @Override
    public Person deletePerson(String email) {
        for(Person person : this.persons){
            if (!Objects.equals(person.getEmail(), email)) {
                this.persons.remove(getPerson(email));
            }
            return person;
        }
        return null;
    }


}