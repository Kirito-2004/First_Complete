package client.models;

import client.network.Client;
import shared.object.Product;
import shared.transferObject.Response;

import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

public class ProductManager implements Manager{
    private Client client;
    private Map<Integer, Product> hashProd;
    public ProductManager(Client client){
        this.client = client;
        hashProd = new HashMap<>();
    }

    public void loadProduct(){
        try {
            Response response = client.request("ListProduct", null);
            hashProd = (Map<Integer, Product>) response.getArg();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void listProduct(){
        loadProduct();
        for (Product product : hashProd.values()){
            System.out.println(product);
        }
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {

    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {

    }
}
