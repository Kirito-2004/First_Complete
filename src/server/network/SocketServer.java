package server.network;
import server.model.AccountManager;
import server.model.ProductManager;

import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer{
    private AccountManager accountManager;
    private ProductManager productManager;
    public SocketServer(){
        System.out.println("\u001B[36m"+"#Loading database"+"\u001B[0m");
        accountManager = new AccountManager();
        accountManager.loadFromFile();
        productManager = new ProductManager();
        productManager.loadFromFile();
    }
    public void startServer(){
        try {
            ServerSocket myServer = new ServerSocket(2004); //Create Server
            System.out.println("\u001B[36m"+"#Server started"+"\u001B[0m");
            while(true) {
                Socket socket = myServer.accept(); //Accept new connection from client
                new Thread(new SocketHandler(socket,accountManager,productManager)).start(); //Create new thread to handle the connection
            } //Auto accept new connection from client
        } catch (Exception e) {
            System.out.println("\u001B[31m"+"<Cannot start server>"+"\u001B[0m");
        }
    }
}
