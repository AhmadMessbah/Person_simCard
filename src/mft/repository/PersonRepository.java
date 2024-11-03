package mft.repository;

import mft.model.entity.Person;
import mft.model.entity.SimCard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonRepository implements AutoCloseable {
    private Connection connection;
    private PreparedStatement preparedStatement;

    public void save(Person person) throws SQLException {
        connection = ConnectionProvider.getProvider().getConnection();

        preparedStatement = connection.prepareStatement(
                "INSERT INTO PERSONS VALUES (?,?,?)"
        );
        preparedStatement.setInt(1, person.getId());
        preparedStatement.setString(2, person.getName());
        preparedStatement.setString(3, person.getFamily());
        preparedStatement.executeUpdate();
    }

    public Person findById(int id) throws Exception {
        connection = ConnectionProvider.getProvider().getConnection();

        preparedStatement = connection.prepareStatement(
                "select * from PERSON_SIM_CARD_VIEW  where person_id = ?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        Person person = null;
        while (resultSet.next()) {
            if(person == null) {
                person =
                        Person
                                .builder()
                                .id(resultSet.getInt("person_id"))
                                .name(resultSet.getString("name"))
                                .family(resultSet.getString("family"))
                                .build();
            }
            SimCard simCard =
                    SimCard
                    .builder()
                            .id(resultSet.getInt("sim_card_id"))
                            .simNumber(resultSet.getString("phone_number"))
                            .owner(person)
                            .build();
         person.addSimCard(simCard);
        }
        return person;
    }

    public List<Person> findAll() throws SQLException {
        connection = ConnectionProvider.getProvider().getConnection();

        preparedStatement = connection.prepareStatement(
                "select * from PERSONS"
        );
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Person> personList = new ArrayList<>();

        while (resultSet.next()) {
            Person person =
                    Person
                            .builder()
                            .id(resultSet.getInt("id"))
                            .name(resultSet.getString("name"))
                            .family(resultSet.getString("family"))
                            .build();
            personList.add(person);
        }
        return personList;
    }


    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
