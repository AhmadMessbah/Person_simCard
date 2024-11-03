package mft.repository;

import mft.model.entity.Person;
import mft.model.entity.SimCard;
import mft.service.PersonService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SimCardRepository implements AutoCloseable {
    private Connection connection;
    private PreparedStatement preparedStatement;

    public void save(SimCard simCard) throws SQLException {
        connection = ConnectionProvider.getProvider().getConnection();

        preparedStatement = connection.prepareStatement(
                "INSERT INTO SIM_CARDS VALUES (?,?,?)"
        );
        preparedStatement.setInt(1, simCard.getId());
        preparedStatement.setString(2, simCard.getSimNumber());
        preparedStatement.setInt(3, simCard.getOwner().getId());
        preparedStatement.executeUpdate();
    }

    public List<SimCard> findAll() throws SQLException {
        connection = ConnectionProvider.getProvider().getConnection();

        preparedStatement = connection.prepareStatement(
                "select * from PERSON_SIM_CARD_VIEW"
        );
        ResultSet resultSet = preparedStatement.executeQuery();

        List<SimCard> simCardList = new ArrayList<>();

        while (resultSet.next()) {
            Person person =
                    Person
                            .builder()
                            .id(resultSet.getInt("person_id"))
                            .name(resultSet.getString("name"))
                            .family(resultSet.getString("family"))
                            .build();

            SimCard simCard =
                    SimCard
                            .builder()
                            .id(resultSet.getInt("sim_card_id"))
                            .simNumber(resultSet.getString("phone_number"))
                            .owner(person)
                            .build();
            simCardList.add(simCard);
        }
        return simCardList;
    }

    public int countSimCardsByPersonId(int personId) throws SQLException {
        connection = ConnectionProvider.getProvider().getConnection();

        preparedStatement = connection.prepareStatement("select count(*) as sim_count from PERSON_SIM_CARD_VIEW where person_id=?");
        preparedStatement.setInt(1, personId);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("sim_count");
    }

    public List<SimCard> findByPersonId(int personId) throws Exception {
        connection = ConnectionProvider.getProvider().getConnection();

        preparedStatement = connection.prepareStatement(
                "select * from PERSON_SIM_CARD_VIEW where person_id=?"
        );
        preparedStatement.setInt(1, personId);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<SimCard> simCardList = new ArrayList<>();

        while (resultSet.next()) {
            Person person =
                    Person
                            .builder()
                            .id(resultSet.getInt("person_id"))
                            .name(resultSet.getString("name"))
                            .family(resultSet.getString("family"))
                            .build();

            SimCard simCard =
                    SimCard
                            .builder()
                            .id(resultSet.getInt("sim_card_id"))
                            .simNumber(resultSet.getString("phone_number"))
                            .owner(person)
                            .build();
            simCardList.add(simCard);
        }
        return simCardList;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
