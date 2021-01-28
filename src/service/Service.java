package service;

import domain.Comanda;
import domain.Produs;
import observer.Observable;
import observer.Observer;
import repository.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Service implements Observable {

    private Repository<String, Produs> repoProduse;
    private Repository<Integer, Comanda> repoComenzi;
    private List<Observer> observers = new ArrayList<>();
    private List<Comanda> comenziNerezolvate = new ArrayList<>();

    public Service(Repository<String, Produs> repoProduse, Repository<Integer, Comanda> repoComenzi) {
        this.repoProduse = repoProduse;
        this.repoComenzi = repoComenzi;
    }

    public List<Produs> getProduse() {
        List<Produs> produse = new ArrayList<>();
        repoProduse.findAll().forEach(produse::add);
        return produse;
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserber(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(x -> x.update());
    }

    public void adaugaProdus(String denumire, int pret) {
        Produs p = new Produs(denumire, pret);
        repoProduse.save(p);
        notifyObservers();
    }

    public void plaseazaComanda(String camera, Produs produsSelectat, int cantitate) {
        int nrCamera = Integer.parseInt(camera);
        int pret = cantitate * produsSelectat.getPret();
        Comanda c = new Comanda(nrCamera, produsSelectat.getDenumire(), pret, LocalDateTime.now());
        comenziNerezolvate.add(c);
        notifyObservers();
    }

    public List<Comanda> getComenziNerezolvate() {
        return comenziNerezolvate;
    }

    public List<Comanda> getComenzi() {
        List<Comanda> comenzi = new ArrayList<>();
        repoComenzi.findAll().forEach(comenzi::add);
        return comenzi;
    }

    public void salveazaComanda(Comanda comanda) {
        int id = repoComenzi.size() + 1;
        comanda.setId(id);
        repoComenzi.save(comanda);
        comenziNerezolvate.remove(comanda);
        notifyObservers();
    }
}
