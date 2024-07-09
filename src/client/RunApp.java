package client;
import client.cores.*;

public class RunApp {
    public static void main(String[] args){
        ClientFactory cf = new ClientFactory();
        ModelFactory mf = new ModelFactory(cf);
        ViewModelFactory vmf = new ViewModelFactory(mf);
        ViewHandler vh = new ViewHandler(vmf);
        vh.startApp();
    }
}
