package controller;

import domain.Comanda;
import domain.Produs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import observer.Observer;
import service.Service;

import java.util.ArrayList;
import java.util.List;

public class ComenziController implements Observer {

    private Service service;
    private String camera;
    ObservableList<Comanda> comenzi = FXCollections.observableArrayList();


    @FXML
    ListView<Comanda> listaComenzi = new ListView();

    @FXML
    Label notaDePlata;

    @FXML
    public void initialize()
    {
        listaComenzi.setItems(comenzi);
    }

    public void setService(Service service,String camera)
    {
        this.service=service;
        this.camera=camera;
        service.addObserver(this);
        comenzi.setAll(getComenziList());
        notaDePlata.setText(String.valueOf(notaDePlata())+" lei");
    }

    public List<Comanda> getComenziList()
    {
        List<Comanda> comenzi=new ArrayList<>();
        int nrCamera=Integer.parseInt(camera);
        service.getComenzi().forEach(x->{
                if(x.getNrCamera()==nrCamera)
                    comenzi.add(x);
                    });
        return comenzi;
    }

    public int notaDePlata()
    {
        int suma=0;
        suma=getComenziList().stream().map(x->x.getPret()).reduce(0, (a, b) -> a + b);
        return suma;
    }

    @Override
    public void update() {
        comenzi.setAll(getComenziList());
        notaDePlata.setText(String.valueOf(notaDePlata())+" lei");
    }
}
