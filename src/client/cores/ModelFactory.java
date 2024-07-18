package client.cores;
import client.models.CustomerManager;
import client.models.ProductManager;

public class ModelFactory {
    private ClientFactory cf;
    public ModelFactory(ClientFactory cf) {
        this.cf = cf;
    }
    public CustomerManager getCustomerManager() {
        return new CustomerManager(cf.getClient());
    }
    public ProductManager getProductManager() {
        return new ProductManager(cf.getClient());
    }
}
