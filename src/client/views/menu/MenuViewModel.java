package client.views.menu;

import client.models.CustomerManager;
import client.models.ProductManager;
import client.views.ViewController;
import client.views.register.RegisterViewController;

public class MenuViewModel {
    private boolean valid = true;
    private String message = "";
    private int choice = 0;
    private boolean loginStatus = false;
    private String action = "";

    public MenuViewModel(CustomerManager customerManager) {
        ProductManager productManager = new ProductManager(customerManager.getClient());
        loginStatus = customerManager.getCustomer() != null;
    }

    public void submit(){
        message = "";
        valid = true;
        if(!loginStatus){
            switch (choice){
                case 1:
                    action = "Register";
                    break;
                case 2:
                    action = "Login";
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    valid = false;
                    message = "\u001B[31m"+"<Invalid choice>"+"\u001B[0m";
            }
        }
        else{
            switch (choice){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    valid = false;
                    message = "\u001B[31m"+"<Invalid choice>"+"\u001B[0m";
            }
        }
    }

    public boolean isValid() {
        return valid;
    }

    public String getMessage() {
        return message;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    public boolean getLoginStatus() {
        return loginStatus;
    }

    public String getAction() {
        return action;
    }
}
