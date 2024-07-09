package server.model;
import server.object.Account;
import shared.transferObject.LogEntry;
import shared.transferObject.Request;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.net.Socket;
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

    public <T> void saveToFile(T account){
        try (FileWriter writer = new FileWriter(path, true)) {
            writer.write(account.toString() + "\n");
        } catch (IOException e) {
            System.out.println("\u001B[31m" + "<Error writing to file>" + "\u001B[0m");
        }
    }

    public Request createAccount(String data){
        String[] messageArr = {
                "<This phone number is not exist>",
                "<This phone number is registed>",
                "<Create account successfully>",
                "<Create account failed>"};

        String[] dataArr = data.split(",");
        String tel = dataArr[1];
        Request request = null;

        if(!personManger.isExist(tel)){
            request = new Request("CreateAccount", "\u001B[31m"+messageArr[0]+"\u001B[0m");
        }
        else if(hashAcc.get(Integer.parseInt(tel))!=null){
            request = new Request("CreateAccount", "\u001B[31m"+messageArr[1]+"\u001B[0m");
        }
        else{
            try {
                Account account = Account.fromString(AccountManager.getLastAcc()+","+data);
                hashAcc.put(account.getTel(), account);
                saveToFile(account);
                request = new Request("CreateAccount", "\u001B[32m"+messageArr[2]+"\u001B[0m");
            }
            catch (IllegalArgumentException e) {
                request = new Request("CreateAccount", "\u001B[31m"+messageArr[3]+"\u001B[0m");
            }
        }
        return request;
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
