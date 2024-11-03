package mft.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import mft.model.entity.Person;
import mft.model.entity.SimCard;
import mft.service.PersonService;
import mft.service.SimCardService;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PersonController implements Initializable {
    @FXML
    private TableView<Person> personTbl;

    @FXML
    private TableColumn<Person, String> nameClm, familyNameClm;

    @FXML
    private TableColumn<Person, Integer> idClm;



    @FXML
    private TableView<SimCard> simcardTbl;

    @FXML
    private TableColumn<SimCard, String> phoneNumberClm;

    @FXML
    private TableColumn<SimCard, Integer> simcardIdClm;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            refreshPersonTable(PersonService.findAll());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        personTbl.setOnMouseReleased(event -> {
        Person person = personTbl.getSelectionModel().getSelectedItem();

            if (person != null) {
            try {
                refreshSimcardTable(SimCardService.findByPersonId(person.getId()));    System.out.println(person);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    });
    }

    private void refreshPersonTable(List<Person> perosnList) {
        ObservableList<Person> observableList = FXCollections.observableList(perosnList);

        idClm.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameClm.setCellValueFactory(new PropertyValueFactory<>("name"));
        familyNameClm.setCellValueFactory(new PropertyValueFactory<>("family"));

        personTbl.setItems(observableList);
    }

    private void refreshSimcardTable(List<SimCard> simcardList) {
        ObservableList<SimCard> observableList = FXCollections.observableList(simcardList);

        simcardIdClm.setCellValueFactory(new PropertyValueFactory<>("id"));
        phoneNumberClm.setCellValueFactory(new PropertyValueFactory<>("simNumber"));

        simcardTbl.setItems(observableList);
    }

}
