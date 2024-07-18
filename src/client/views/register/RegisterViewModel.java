package client.views.register;

import client.models.CustomerManager;

public class RegisterViewModel {
    private Boolean valid = true;
    private String message = "";
    private final String[] messageArr = {
    "<Invalid name>",
    "<Invalid phone number>",
    "<Invalid password>",
    "<Password does not match>"
    };

    private String name;
    private String tel;
    private String pass;
    private String rePass;

    private final CustomerManager customerManager;

    public RegisterViewModel(CustomerManager customerManager) {
        this.name = "";
        this.tel = "";
        this.pass = "";
        this.rePass="";
        this.customerManager = customerManager;
    }

    public void submit() {
        customerManager.createAccount(name, tel, pass);
        message = customerManager.getMessage();
    }

    public boolean isValid() {
        return valid;
    }

    public String getMessage() {
        return message;
    }

    public void setName(String name) {
        this.name = name;
        this.valid = true;
        this.message = "";
        if (!name.matches("^[a-zA-Z\\s]+$")) {
            message = "\u001B[31m" + messageArr[0] + "\u001B[0m";
            valid = false;
        }
    }

    public String getName() {
        return name;
    }

    public void setTel(String tel) {
        this.tel = tel;
        this.valid = true;
        this.message = "";
        if (!tel.matches("^[0-9]{7}$")) {
            valid = false;
            message = "\u001B[31m" + messageArr[1] + "\u001B[0m";
        }
    }

    public void setPass(String pass){
        this.pass = pass;
        this.valid = true;
        this.message = "";
        if (!pass.matches("^.{8,}$")) {
            valid = false;
            message = "\u001B[31m" + messageArr[2] + "\u001B[0m";
        }
    }

    public void setRePass(String rePass) {
        this.rePass = rePass;
        this.valid = true;
        this.message = "";
        if (!this.rePass.equals(pass)) {
            valid = false;
            message = "\u001B[31m" + messageArr[3] + "\u001B[0m";
        }
    }
}
