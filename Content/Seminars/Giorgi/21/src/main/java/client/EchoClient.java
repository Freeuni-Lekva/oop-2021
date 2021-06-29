package client;

import shared.Command;
import shared.Student;
import shared.StudentDao;
import shared.filter.descriptor.ExpressionDescription;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class EchoClient implements StudentDao {
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
        // out.writeObject(Command.EXIT);
        inp.close();
        out.close();
        server.close();
    }

//    public String echo(String msg) throws IOException, ClassNotFoundException {
//        out.writeObject(Command.ECHO);
//        out.writeObject(msg);
//        out.flush();
//        return (String) inp.readObject();
//    }

    public Integer add(Student st) {
        try {
            out.writeObject("add");
            out.writeObject(st);
            out.flush();
            return (Integer) inp.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Student get(int id) {
        try {
            out.writeObject("get");
            out.writeObject(id);
            out.flush();
            return (Student) inp.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Student> filter(ExpressionDescription ed) {
        try {
            out.writeObject("filter");
            out.writeObject(ed);
            out.flush();
            return (List<Student>) inp.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Void remove(Student st) {
        try {
            out.writeObject("remove");
            out.writeObject(st);
            out.flush();
            return (Void) inp.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Student> getAll() {
        try {
            out.writeObject("getAll");
            out.flush();
            return (List<Student>) inp.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
