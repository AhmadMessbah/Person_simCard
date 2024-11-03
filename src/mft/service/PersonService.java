package mft.service;

import mft.model.entity.Person;
import mft.repository.PersonRepository;

import java.util.List;

public class PersonService {
    public static void save(Person person) throws Exception {
        try(PersonRepository repository = new PersonRepository()){
            repository.save(person);
        }
    }

    public static List<Person> findAll() throws Exception {
        try(PersonRepository repository = new PersonRepository()){
            return repository.findAll();
        }
    }

    public static Person findById(int id) throws Exception {
        try(PersonRepository repository = new PersonRepository()){
            return repository.findById(id);
        }
    }
}
