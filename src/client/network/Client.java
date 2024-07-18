package client.network;

import shared.transferObject.Response;

import java.io.IOException;

public interface Client {
    void startClient();
    Response request(String type, String arg) throws IOException, ClassNotFoundException;
}