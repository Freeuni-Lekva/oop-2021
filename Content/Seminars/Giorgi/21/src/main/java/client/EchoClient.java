package client;

import shared.Command;
import shared.Filter;
import shared.Student;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class EchoClient {
    private String address;
    private int port;
    private Socket server;
    private ObjectInputStream inp;
    private ObjectOutputStream out;

    public EchoClient(String address, int port) {
        this.address = address;
        this.port = port;
    }

    public void connect() throws IOException {
        server = new Socket(address, port);
        out = new ObjectOutputStream(server.getOutputStream());
        inp = new ObjectInputStream(server.getInputStream());
    }

    public void disconnect() throws IOException {
        out.writeObject(Command.EXIT);
        inp.close();
        out.close();
        server.close();
    }

    public String echo(String msg) throws IOException {
        out.writeObject(Command.ECHO);
        out.writeObject(msg);
        out.flush();
        try {
            return (String) inp.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int createStudent(Student st) throws IOException {
        out.writeObject(Command.CREATE);
        out.writeObject(st);
        out.flush();
        int id = inp.readInt();
        st.setId(id);
        return id;
    }

    public Student getStudent(int id) throws IOException {
        out.writeObject(Command.GET);
        out.writeInt(id);
        out.flush();
        try {
            return (Student) inp.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Student> filterStudents(Filter f) throws IOException {
        out.writeObject(Command.FILTER);
        out.writeObject(f);
        out.flush();
        try {
            return (List<Student>) inp.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
