package observer;

public interface Observable {

    void addObserver(Observer o);
    void removeObserber(Observer o);
    void notifyObservers();
}
