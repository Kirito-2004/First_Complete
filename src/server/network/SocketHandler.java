package server.network;

import server.model.AccountManager;
import server.object.Account;
import shared.transferObject.Request;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketHandler implements Runnable{
    private Socket socket;
    private AccountManager accountManager;

    private ObjectOutputStream outToClient;
    private ObjectInputStream inFromClient;

    public SocketHandler(Socket socket, AccountManager accountManager){
        this.socket = socket;
        this.accountManager = accountManager;
        //Send and receive data
        try {
            outToClient = new ObjectOutputStream(socket.getOutputStream());
            inFromClient = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        try {
            Request request = (Request) inFromClient.readObject();
            String data = (String) request.getArg();
            if("Listener".equals(request.getType())){
                accountManager.addListener("NewLogEntry", this::onNewLogEntry);
            }
            else if("CreateAccount".equals(request.getType())) {
                Request r = accountManager.createAccount(data);
                outToClient.writeObject(r);
            }
            else if("Login".equals(request.getType())) {

            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void onNewLogEntry(PropertyChangeEvent evt) {
        try {
            outToClient.writeObject(new Request(evt.getPropertyName(), evt.getNewValue()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
