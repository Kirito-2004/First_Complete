package client.views.menu;
import client.cores.*;
import client.models.CustomerManager;
import client.views.ViewController;
import shared.object.Customer;

public class MenuViewModel {
    private CustomerManager customerManager;
    public MenuViewModel(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }

    public  void loadMainMenu() {
        customerManager.loadMainMenu();
    }


}
