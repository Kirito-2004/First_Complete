package client.cores;
import client.models.CustomerManager;

public class ModelFactory {

    private final ClientFactory cf;

    public ModelFactory(ClientFactory cf) {
        this.cf = cf;
    }

    public CustomerManager getCustomerManager() {
        return new CustomerManager(cf.getClient());
    }
}
