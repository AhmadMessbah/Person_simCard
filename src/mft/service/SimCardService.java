package mft.service;

import mft.model.entity.SimCard;
import mft.repository.SimCardRepository;

import java.sql.SQLException;
import java.util.List;

public class SimCardService {
    public static void save(SimCard simCard) throws Exception {
        try(SimCardRepository repository = new SimCardRepository()){
            repository.save(simCard);
        }
    }

    public static List<SimCard> findAll() throws Exception {
        try(SimCardRepository repository = new SimCardRepository()){
            return repository.findAll();
        }
    }

    public static int countSimCardsByPersonId(int personId) throws Exception {
        try(SimCardRepository repository = new SimCardRepository()){
            return repository.countSimCardsByPersonId(personId);
        }
    }
}
