package client.models;
import client.network.Client;
import client.network.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CustomerManager implements Manager{
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    private Client client;

    public CustomerManager(Client client) {
        this.client = client;
        client.startClient();
    }

    public void loadMainMenu(){
        client.loadMainMenu();
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {

    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {

    }
}
