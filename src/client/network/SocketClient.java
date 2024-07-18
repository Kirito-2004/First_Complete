package client.network;
import shared.object.*;
import shared.transferObject.*;

import java.beans.PropertyChangeSupport;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class SocketClient implements Client {
    private String host = "localhost";
    private int port = 2004;
    private PropertyChangeSupport support;

    public SocketClient(){
        support = new PropertyChangeSupport(this);
    }

    public void startClient() {
        Socket socket = new Socket();
        try {
            socket = new Socket(host, port);
            ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());
            new Thread(() -> listenToServer(outToServer, inFromServer)).start();
        } catch (Exception e) {
            System.out.println("\u001B[31m" + "<Cannot connect server>" + "\u001B[0m");
            System.out.println("\u001B[36m" + "Reconnecting..." + "\u001B[0m");
        }
    }

    public Response request(String type, String arg) throws IOException, ClassNotFoundException {
        Socket socket = new Socket(host, port);
        ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());
        outToServer.writeObject(new Request(type, arg));
        return (Response) inFromServer.readObject();
    }

    private void listenToServer(ObjectOutputStream outToServer, ObjectInputStream inFromServer) {
        try {
            outToServer.writeObject(new Request("Listener", null));
            while (true) {
                Request request = (Request) inFromServer.readObject();
                support.firePropertyChange(request.getType(), null, request.getArg());
            }
        } catch (Exception e) {
            System.out.println();
            System.out.println("\u001B[31m" + "<Server disconnected>" + "\u001B[0m");
            System.out.println("\u001B[36m" + "#Reconnecting..." + "\u001B[0m");
            startClient();
        }
    }
}
