package server;

import shared.Command;
import shared.Filter;
import shared.Student;
import shared.StudentDao;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class EchoServer {
    private int port;
    private StudentDao students;


    public EchoServer(int port, StudentDao students) {
        this.port = port;
        this.students = students;
    }

    public void start() throws IOException {
        ServerSocket ss = new ServerSocket(port);
        System.out.println("SERVER: Server socket created");
        while (true) {
            System.out.println("SERVER: Waiting for client connection");
            Socket client = ss.accept();
            System.out.println("SERVER: Client connection accepted");
            handleClient(client);
        }
    }

    private void handleClient(Socket client) {
        new Thread(() -> {
            try {
                ObjectInputStream inp = new ObjectInputStream(client.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
                while (true) {
                    Command cmd = (Command) inp.readObject();
                    switch (cmd) {
                        case EXIT:
                            inp.close();
                            out.close();
                            client.close();
                            break;
                        case ECHO:
                            String msg = (String) inp.readObject();
                            out.writeObject(msg);
                            break;
                        case CREATE:
                            Student st = (Student) inp.readObject();
                            students.add(st);
                            out.writeInt(st.getId());
                            break;
                        case GET:
                            int id = inp.readInt();
                            out.writeObject(students.get(id));
                            break;
                        case FILTER:
                            Filter f = (Filter) inp.readObject();
                            out.writeObject(students.filter(f));
                            break;
                    }
                    out.flush();
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void shutdown() {

    }

}
