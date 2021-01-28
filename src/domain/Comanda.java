package domain;

import java.time.LocalDateTime;

public class Comanda extends Entity<Integer>{

    private int nrCamera;
    private String produs;
    private int pret;
    private LocalDateTime data;

    public Comanda(int nrCamera, String produs, int pret, LocalDateTime data) {
        this.nrCamera = nrCamera;
        this.produs = produs;
        this.pret = pret;
        this.data = data;
    }

    public int getNrCamera() {
        return nrCamera;
    }

    public void setNrCamera(int nrCamera) {
        this.nrCamera = nrCamera;
    }

    public String getProdus() {
        return produs;
    }

    public void StringProdus(String produs) {
        this.produs = produs;
    }

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "Camera"+this.getNrCamera()+": "+this.getProdus()+" ,pret: "+this.getPret()+" lei ,ora:"+this.getData().getHour();
    }
}
