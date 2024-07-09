package server.object;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Person {
    private int idCard;
    private String name;
    private String gender;
    private Date dob;
    private String address;
    private int tel;



    public Person(){
        this.idCard = 0;
        this.name = "None";
        this.gender= "None";
        this.tel = 0;
        this.address = "None";
        this.dob = new Date();
    }

    public Person(int idCard, String name, String gender, String dob, String address, int tel){
        this.idCard = idCard;
        this.name = name;
        this.gender = gender;
        this.tel = tel;
        this.address = address;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        try {
            date = formatter.parse(dob);
        }
        catch (Exception ex){
            System.out.println("Lỗi định dạng!");
        }
        this.dob = date;
    }
    public int getIdCard(){
        return idCard;
    }
    public void setIdCard(int idCard){
        this.idCard = idCard;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getGender(){
        return gender;
    }
    public void setGender(String gender){
        this.gender = gender;
    }

    public int getTel(){
        return tel;
    }
    public void setPhone(int tel){
        this.tel = tel;
    }

    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }

    public Date getDob(){
        return dob;
    }
    public void setDob(Date dob){
        this.dob=dob;
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String date = formatter.format(this.dob);
        return this.idCard + "," + this.name + "," + this.gender + "," + date + "," + this.address + "," + this.tel;
    }

    public static Person fromString(String str) {
        String[] parts = str.split(",");
        if (parts.length < 6) {
            throw new IllegalArgumentException("Invalid person data: " + str);
        }
        int idCard = Integer.parseInt(parts[0]);
        String name = parts[1];
        String gender = parts[2];
        String dob = parts[3];
        String address = parts[4];
        int tel = Integer.parseInt(parts[5]);
        return new Person(idCard,name,gender,dob,address,tel);
    }
}
