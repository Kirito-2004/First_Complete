package server;
import server.network.SocketServer;

public class RunServer {
    public static void main(String[] args) {
        SocketServer ss = new SocketServer();
        ss.startServer();
    }
}