package controller;

import domain.Comanda;
import domain.Produs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import observer.Observer;
import service.Service;

import java.awt.*;
import java.io.IOException;

public class ClientController implements Observer {

    private Service service;
    private String camera;
    private ObservableList<Produs> produse = FXCollections.observableArrayList();



    @FXML
    ListView<Produs> listaProduse = new ListView();
    @FXML
    Button comandaButton;
    @FXML
    Button comenziButton;
    @FXML
    TextField cantitateTextField;


    @FXML
    public void initialize() {

        listaProduse.setItems(produse);
       // listaProduse.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void setService(Service service, String camera) {
        this.service = service;
        this.camera = camera;
        service.addObserver(this);
        produse.setAll(service.getProduse());


    }

    @FXML
    public void comanda() {
        Produs produsSelectat = listaProduse.getSelectionModel().getSelectedItem();
        if (produsSelectat != null) {
            int cantitate;
            String cantitateString = cantitateTextField.getText();
            try {
                if (cantitateString.equals("")) {
                    cantitate = 1;
                } else {
                    cantitate = Integer.parseInt(cantitateTextField.getText());
                }
                service.plaseazaComanda(camera, produsSelectat, cantitate);

            } catch (NumberFormatException e) {
                MessageAlert.showWarningMessage(null, "Cantitatea trebuie sa fie un nr intreg");
            }
        } else {
            MessageAlert.showWarningMessage(null, "Nu ati selectat un produs");
        }

    }

    @Override
    public void update() {
        produse.setAll(service.getProduse());

    }

    @FXML
    public void veziComenzi() {
        try {
            FXMLLoader loaderM = new FXMLLoader();
            loaderM.setLocation(getClass().getResource("/sample/listaComenzi.fxml"));
            AnchorPane rootM = loaderM.load();
            Stage dialogStageM = new Stage();
            dialogStageM.setTitle("Nota de plata");
            Scene sceneM = new Scene(rootM, 300, 400);
            dialogStageM.setScene(sceneM);
            ComenziController comenziController = loaderM.getController();
            comenziController.setService(service, camera);
            dialogStageM.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
