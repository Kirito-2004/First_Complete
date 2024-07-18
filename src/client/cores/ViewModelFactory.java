package client.cores;
import client.views.menu.MenuViewModel;
import client.views.register.RegisterViewModel;

public class ViewModelFactory {
    private final ModelFactory mf;
    private MenuViewModel menuViewModel;
    private RegisterViewModel registerViewModel;
    public ViewModelFactory(ModelFactory mf) {
        this.mf = mf;
    }
    public MenuViewModel getMenuViewModel() {
        if (menuViewModel == null)
            menuViewModel = new MenuViewModel(mf.getCustomerManager());
        return menuViewModel;
    }
    public RegisterViewModel getRegisterViewModel() {
        if (registerViewModel == null)
            registerViewModel = new RegisterViewModel(mf.getCustomerManager());
        return registerViewModel;
    }
}