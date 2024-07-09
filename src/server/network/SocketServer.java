package server.network;
import server.model.AccountManager;
import server.model.PersonManger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer{
    AccountManager accountManager;
    public SocketServer(){
        System.out.println("\u001B[36m"+"#Loading data"+"\u001B[0m");
        accountManager = new AccountManager();
        accountManager.loadFromFile();
    }
    public void startServer(){
        try {
            ServerSocket myServer = new ServerSocket(2004); //Create Server
            System.out.println("\u001B[36m"+"#Server started"+"\u001B[0m");
            while(true) {
                Socket socket = myServer.accept(); //Accept new connection from client
                new Thread(new SocketHandler(socket,accountManager)).start(); //Create new thread to handle the connection
            } //Auto accept new connection from client
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
