package controller;

import domain.Comanda;
import domain.Produs;
import domain.validator.ValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import observer.Observable;
import observer.Observer;
import service.Service;

import java.awt.*;
import java.security.Policy;

public class AdministratorController implements Observer {

    private Service service;
    ObservableList<Produs> produse= FXCollections.observableArrayList();
    ObservableList<Comanda> comenzi= FXCollections.observableArrayList();


    @FXML
    ListView<Produs> listaProduse=new ListView();
    @FXML
    ListView<Comanda> listaComenzi = new ListView();

    @FXML
    TextField denumireTextField;
    @FXML
    TextField pretTextField;
    @FXML
    Button adaugaProduseButton;
    @FXML
    Button rezolvaButton;

    @FXML
    public void initialize()
    {
        listaProduse.setItems(produse);
    }

    public void setService(Service service)
    {
        this.service=service;
        service.addObserver(this);
        produse.setAll(service.getProduse());
        listaComenzi.setItems(comenzi);
        comenzi.setAll(service.getComenziNerezolvate());

    }

    @Override
    public void update() {
        produse.setAll(service.getProduse());
        comenzi.setAll(service.getComenziNerezolvate());
    }

    @FXML
    public void adaugaProdus() {
            String denumire=denumireTextField.getText();
            if(denumire.equals(""))
                MessageAlert.showWarningMessage(null,"Introduceti o denumire");
            else {
                int pret;
                try {
                    pret = Integer.parseInt(pretTextField.getText());
                    try{
                        service.adaugaProdus(denumire,pret);
                    }catch(ValidationException v)
                    {
                        MessageAlert.showWarningMessage(null,v.getMessage());
                    }

                } catch (NumberFormatException ne) {
                    MessageAlert.showWarningMessage(null, "Pt pret trebuie introdus un nr intreg");
                }
            }

            denumireTextField.clear();
            pretTextField.clear();
    }

    @FXML
    public void rezolvaComenzi(){
            Comanda comandaSelectata=listaComenzi.getSelectionModel().getSelectedItem();
            if(comandaSelectata!=null)
            {
                    service.salveazaComanda(comandaSelectata);
            }
            else{
                MessageAlert.showWarningMessage(null,"Nu ati selectat nicio comanda!");
            }
    }
}
