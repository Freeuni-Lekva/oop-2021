import shared.Command;
import shared.LastNameFilter;
import shared.Student;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class Client {

    private int port;
    private String address;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;


    public Client(int port){
        this.port = port;
        address = "localhost";
    }

    public void start() {
        new Thread(() -> {
            try {
                socket = new Socket(address, port);
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());

                out.writeObject(Command.ECHO);
                out.writeObject("test string");
                System.out.println("Echo result: " + in.readObject());

                int id = 10;
                out.writeObject(Command.CREATE);
                out.writeObject(new Student(id, "giorgi", "pirveli", 2020));

                out.writeObject(Command.CREATE);
                out.writeObject(new Student(12, "giorgi", "meore", 2021));


                out.writeObject(Command.GET);
                out.writeObject(id);
                System.out.println("Get response: " + in.readObject());

                out.writeObject(Command.FILTER);
                out.writeObject(new LastNameFilter("meore"));
                List<Student> students = (List<Student>) in.readObject();
                for (Student student: students){
                    System.out.println(student);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
