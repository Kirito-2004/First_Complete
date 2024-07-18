package server.model;

import shared.object.Product;
import shared.transferObject.LogEntry;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductManager implements Manager{
    private String path = "data/product.txt";
    private PropertyChangeSupport support;
    private List<LogEntry> logEntries;

    private static int lastProd = 0;
    public static void setLastProd(int n) {lastProd = n;}
    public static int getLastProd() {return lastProd;}

    private Map<Integer, Product> hashProd;

    public ProductManager() {
        support = new PropertyChangeSupport(this);
        logEntries = new ArrayList<>();
        hashProd = new HashMap<>();
    }

    public Map<Integer, Product> getHashProd() {
        return hashProd;
    }

    @Override
    public void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Product product = Product.fromString(line);
                    hashProd.put(product.getIdProd(), product);
                    if (product.getIdProd() > ProductManager.getLastProd()) {
                        ProductManager.setLastProd(product.getIdProd() + 1);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("\u001B[31m" + "<Skipping invalid product data: " + line + ">" + "\u001B[0m");
                }
            }
            System.out.println("\u001B[32m" + "<Load product successfully>" + "\u001B[0m");
        } catch (IOException e) {
            System.out.println("\u001B[31m" + "<Error reading from file>" + "\u001B[0m");
        }
    }

    @Override
    public void saveToFile(String strObj) {
        try (FileWriter writer = new FileWriter(path, true)) {
            writer.write(strObj + "\n");
        } catch (IOException e) {
            System.out.println("\u001B[31m" + "<Error writing to file>" + "\u001B[0m");
        }
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);
    }
}
