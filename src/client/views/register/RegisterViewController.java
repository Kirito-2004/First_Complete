package client.views.register;

import client.cores.ViewHandler;
import client.cores.ViewModelFactory;
import client.views.ViewController;

import java.util.Scanner;

public class RegisterViewController implements ViewController {
    Scanner sc = new Scanner(System.in);

    private String messageLabel;
    private String nameField;
    private String telField;
    private String passField;
    private String rePassField;

    private RegisterViewModel registerViewModel;
    private ViewHandler vh;

    @Override
    public void init(ViewHandler vh, ViewModelFactory vmf) {
        this.vh = vh;
        this.registerViewModel = vmf.getRegisterViewModel();
        messageLabel="";
        nameField="";
        telField="";
        passField="";
        rePassField="";
        register();
    }
    public void register(){
        //Input from keyboard
        do {
            nameField = "";
            System.out.print("Enter your name: ");
            nameField = sc.nextLine();
            registerViewModel.setName(nameField);
            messageLabel = registerViewModel.getMessage();
            if(!messageLabel.isEmpty()) System.out.println(messageLabel);
        } while (!registerViewModel.isValid());

        do {
            telField = "";
            System.out.print("Enter your phone number: ");
            telField = sc.nextLine();
            registerViewModel.setTel(telField);
            messageLabel = registerViewModel.getMessage();
            if(!messageLabel.isEmpty()) System.out.println(messageLabel);
        } while (!registerViewModel.isValid());

        do {
            passField = "";
            System.out.print("Enter your password: ");
            passField = sc.nextLine();
            registerViewModel.setPass(passField);
            messageLabel = registerViewModel.getMessage();
            if(!messageLabel.isEmpty()) System.out.println(messageLabel);
        } while (!registerViewModel.isValid());

        do {
            rePassField = "";
            System.out.print("Re-enter your password: ");
            rePassField = sc.nextLine();
            registerViewModel.setRePass(rePassField);
            messageLabel = registerViewModel.getMessage();
            if(!messageLabel.isEmpty()) System.out.println(messageLabel);
        } while (!registerViewModel.isValid());

        //Submit
        registerViewModel.submit();
        messageLabel = registerViewModel.getMessage();
        System.out.println(messageLabel);

        vh.openMainMenu("");
    }
}
