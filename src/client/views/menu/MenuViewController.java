package client.views.menu;

import client.cores.ViewHandler;
import client.cores.ViewModelFactory;
import client.views.ViewController;

public class MenuViewController implements ViewController {
    private MenuViewModel viewModel;
    private ViewHandler vh;

    @Override
    public void init(ViewHandler vh, ViewModelFactory vmf) {
        this.vh = vh;
        this.viewModel = vmf.getMenuViewModel();
    }
}
