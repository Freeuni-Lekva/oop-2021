package rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketClient {
    protected Socket server;
    protected ObjectInputStream inp;
    protected ObjectOutputStream out;

    public void connect(String address, int port) throws IOException {
        server = new Socket(address, port);
        out = new ObjectOutputStream(server.getOutputStream());
        inp = new ObjectInputStream(server.getInputStream());
    }
}
