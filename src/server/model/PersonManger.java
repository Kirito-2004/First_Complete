package server.model;

import server.object.Person;

import java.beans.PropertyChangeListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class PersonManger implements Manager {
    private String path = "data/person.txt";
    private static int lastPerson;
    public static void setLastPerson(int n) {lastPerson = n;}
    public static int getLastPerson() {return lastPerson;}


    private HashMap<Integer,Person> hashPerson;
    private HashMap<Integer,Integer> hashTel;
    public PersonManger(){
        hashPerson = new HashMap<>();
        hashTel = new HashMap<>();
    }

    public void loadFromFile(){
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Person person = Person.fromString(line);
                    hashPerson.put(person.getIdCard(), person);
                    hashTel.put(person.getTel(),person.getIdCard());
                    if (person.getIdCard() > getLastPerson()) {
                        setLastPerson(person.getIdCard() + 1);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("\u001B[31m" + "<Skipping invalid person data: " + line + ">" + "\u001B[0m");
                }
            }
            System.out.println("\u001B[32m" + "<Load person successfully>" + "\u001B[0m");
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
    
    public void addPerson(Person person){
        this.hashPerson.put(person.getIdCard(),person);
    }

    public HashMap<Integer,Person> getListPerson(){
        return this.hashPerson;
    }
    public Person find(String input){
        if(input.length()==6){
            return this.hashPerson.get(Integer.parseInt(input));
        }
        else if(input.length()==7){
            return this.hashPerson.get(this.hashTel.get(Integer.parseInt(input)));
        }
        return null;
    }
    public boolean isExist(String input){
        return find(input) != null;
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {

    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {

    }
}
