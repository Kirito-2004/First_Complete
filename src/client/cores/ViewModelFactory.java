package client.cores;
import client.views.*;
import client.views.menu.MenuView;
import client.views.menu.MenuViewModel;

public class ViewModelFactory {
    private final ModelFactory model;
    private MenuViewModel menuViewModel;
    public ViewModelFactory(ModelFactory model) {
        this.model = model;
    }
    public MenuViewModel getMenuViewModel() {
        if (menuViewModel == null)
            menuViewModel = new MenuViewModel(model.getCustomerManager());
        return menuViewModel;
    }
}