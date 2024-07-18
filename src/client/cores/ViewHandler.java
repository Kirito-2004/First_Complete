package client.cores;

import client.views.ViewController;
import client.views.menu.MenuView;
import client.views.menu.MenuViewController;
import client.views.register.RegisterView;
import client.views.register.RegisterViewController;

public class ViewHandler {
    private ViewModelFactory vmf;
    public ViewHandler(ViewModelFactory vmf) {
        this.vmf = vmf;
    }

    public void startApp() {
        openMainMenu("");
    }

    public void openMainMenu(String type){
        loadView("MainMenu"+type);
    }

    public void openRegister(){
        loadView("Register");
    }

    private void loadView(String view){
        switch (view) {
            case "MainMenu" -> {
                MenuView menuView = new MenuView();
                menuView.title();
                ViewController vc = new MenuViewController();
                vc.init(this, vmf);
            }
            case "MainMenuA" -> {
                MenuView menuView = new MenuView();
                menuView.menuA();
            }
            case "MainMenuB" -> {
                MenuView menuView = new MenuView();
                menuView.menuB();
            }
            case "Register" -> {
                RegisterView registerView = new RegisterView();
                registerView.title();
                ViewController vc = new RegisterViewController();
                vc.init(this, vmf);
            }
            case "Login" -> System.out.println("Login");
            case "ListProduct" -> System.out.println("List Product");
            default -> System.out.println("View not found");
        }
    }
}
