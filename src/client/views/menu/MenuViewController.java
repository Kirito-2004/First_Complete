package client.views.menu;

import client.cores.ViewHandler;
import client.cores.ViewModelFactory;
import client.views.ViewController;

import java.util.Scanner;

public class MenuViewController implements ViewController {
    Scanner sc = new Scanner(System.in);
    private String messageLabel;
    private int choiceField;

    private MenuViewModel menuViewModel;
    private ViewHandler vh;

    @Override
    public void init(ViewHandler vh, ViewModelFactory vmf) {
        this.vh = vh;
        this.menuViewModel = vmf.getMenuViewModel();
        messageLabel = "";
        choiceField = 0;
        if(!menuViewModel.getLoginStatus()){
            vh.openMainMenu("A");
        }
        else{
            vh.openMainMenu("B");
        }
        loadMainMenu();
    }

    public void loadMainMenu(){
        do{
            choiceField = getChoice();
            menuViewModel.setChoice(choiceField);
            menuViewModel.submit();
            messageLabel = menuViewModel.getMessage();
            if(!messageLabel.isEmpty()) System.out.println(messageLabel);
        } while (!menuViewModel.isValid());

        switch (menuViewModel.getAction()) {
            case "Register":
                vh.openRegister();
                break;
            case "Login":
                break;
            default:
        }
    }

    public int getChoice() {
        sc = new Scanner(System.in);
        int choice = 0;
        try {
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
        } catch (Exception ex) {}
        return choice;
    }
}
