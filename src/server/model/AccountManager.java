package server.model;
import server.object.Account;
import shared.transferObject.LogEntry;
import shared.transferObject.Request;
import shared.transferObject.Response;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class AccountManager implements Manager{
    private PropertyChangeSupport support;
    private ArrayList<LogEntry> logEntries;
    private String path = "data/account.txt";
    private static int lastAcc;
    public static void setLastAcc(int n) {lastAcc = n;}
    public static int getLastAcc() {return lastAcc;}

    private HashMap<Integer, Account> hashAcc;
    private PersonManger personManger;

    public AccountManager(){
        support = new PropertyChangeSupport(this);
        logEntries = new ArrayList<>();
        hashAcc = new HashMap<>();
        personManger = new PersonManger();
        personManger.loadFromFile();
    }

    public void loadFromFile(){
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Account account = Account.fromString(line);
                    hashAcc.put(account.getTel(), account);
                    if (account.getIdAcc() > getLastAcc()) {
                        setLastAcc(account.getIdAcc() + 1);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("\u001B[31m" + "<Skipping invalid account data: " + line + ">" + "\u001B[0m");
                }
            }
            System.out.println("\u001B[32m" + "<Load account successfully>" + "\u001B[0m");
        } catch (IOException e) {
            System.out.println("\u001B[31m" + "<Error reading from file>" + "\u001B[0m");
        }
    }

    public void saveToFile(String strObj){
        try (FileWriter writer = new FileWriter(path, true)) {
            writer.write(strObj + "\n");
        } catch (IOException e) {
            System.out.println("\u001B[31m" + "<Error writing to file>" + "\u001B[0m");
        }
    }

    public Response createAccount(String data){
        String[] messageArr = {
                "<This phone number is not exist>",
                "<This phone number is registed>",
                "<Create account successfully>",
                "<Create account failed>"};

        String[] dataArr = data.split(",");
        String tel = dataArr[1];
        Response response = null;

        if(!personManger.isExist(tel)){
            response = new Response( "\u001B[31m"+messageArr[0]+"\u001B[0m",null);
        }
        else if(hashAcc.get(Integer.parseInt(tel))!=null){
            response = new Response( "\u001B[31m"+messageArr[1]+"\u001B[0m",null);
        }
        else{
            try {
                Account account = Account.fromString(AccountManager.getLastAcc()+","+data);
                hashAcc.put(account.getTel(), account);
                saveToFile(account.toString());
                response = new Response( "\u001B[32m"+messageArr[2]+"\u001B[0m",null);
            }
            catch (IllegalArgumentException e) {
                response = new Response( "\u001B[31m"+messageArr[3]+"\u001B[0m",null);
            }
        }
        return response;
    }

    public Response login(String data){
        String[] messageArr = {
                "<This account is not exist>",
                "<This account is not correct>",
                "<Login successfully>",
                "<Login failed>"};

        String[] dataArr = data.split(",");
        String tel = dataArr[0];
        String password = dataArr[1];
        Response response = null;

        Account account = hashAcc.get(Integer.parseInt(tel));
        if(account==null){
            response = new Response( "\u001B[31m"+messageArr[0]+"\u001B[0m",null);
        }
        else if(!account.getPassword().equals(password)){
            response = new Response( "\u001B[31m"+messageArr[1]+"\u001B[0m",null);
        }
        else{
            response = new Response( "\u001B[32m"+messageArr[2]+"\u001B[0m",account.getCustomer());
        }
        return response;
    }

    public void removeAccount(String username){
        //Remove account from account list
    }
    public void updateAccount(String username, String password){
        //Update account information
    }

    public void addListener(String eventName, PropertyChangeListener listener){
        support.addPropertyChangeListener(eventName, listener);
    }
    public void removeListener(String eventName, PropertyChangeListener listener){
        support.removePropertyChangeListener(eventName, listener);
    }
}
