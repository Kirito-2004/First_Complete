package client.cores;

public class ViewHandler {
    private ViewModelFactory vmf;

    public ViewHandler(ViewModelFactory vmf) {
        this.vmf = vmf;
    }

    public void startApp() {
        while (true) {
            openMainMenu();
        }
    }

    public void openMainMenu() {
        vmf.getMenuViewModel().loadMainMenu();
    }
}
