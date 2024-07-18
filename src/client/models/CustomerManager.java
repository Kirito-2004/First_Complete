package client.models;
import client.network.Client;
import client.network.*;
import shared.object.Customer;
import shared.transferObject.Request;
import shared.transferObject.Response;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Scanner;

public class CustomerManager implements Manager{
    Scanner sc = new Scanner(System.in);
    private String message = "";
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    private Client client;
    private Customer customer;

    public CustomerManager(Client client) {
        this.client = client;
        client.startClient();
    }

    public Customer getCustomer(){
        return customer;
    }

    public Client getClient(){
        return client;
    }

    public void createAccount(String name, String tel, String pass) {
        try {
            Response response = client.request("CreateAccount", name + "," + tel + "," + pass);
            message = (String)response.getMessage();
        }
        catch(Exception e) {
            message = "Server not responding";
        }
    }

    public void login() {
        sc = new Scanner(System.in);
        String[] messageArr = {
                "<Invalid phone number>",
                "<Invalid password>"};
        try {
            System.out.println("\u001B[36m" + "#Login:" + "\u001B[0m");
            String tel = "";
            do {
                System.out.print("Enter your phone number: ");
                tel = sc.nextLine();
                if(!tel.matches("^[0-9]{7}$")){
                    System.out.println("\u001B[31m" + messageArr[0] + "\u001B[0m");
                }
            } while(!tel.matches("^[0-9]{7}$"));

            String pass = "";
            do {
                System.out.print("Enter your password: ");
                pass = sc.nextLine();
                if(!pass.matches("^.{8,}$")){
                    System.out.println("\u001B[31m" + messageArr[1] + "\u001B[0m");
                }
            } while (!pass.matches("^.{8,}$"));

            Response response = client.request("Login", tel + "," + pass);
            String message = (String)response.getMessage();
            System.out.println(message);
            Customer customer = (Customer)response.getArg();
            if(response.getArg() != null){
                this.customer = customer;
            }
        }
        catch(Exception e) {
            System.out.println("Server not responding");
        }
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {

    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {

    }
}
