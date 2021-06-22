import shared.ArrayListStudentDao;
import shared.Command;
import shared.Filter;
import shared.Student;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {
    private int port;
    private ServerSocket serverSocket;
    private ArrayListStudentDao store;

    public Server(int port){
        this.port = port;
        store = new ArrayListStudentDao();
    }

    public void start(){
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(port);

                while (true) {
                    System.out.println("Waiting for the connection...");
                    Socket socket = serverSocket.accept();
                    System.out.println(socket.getPort());
                    handleClient(socket);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void handleClient(Socket socket){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

                    while(true){
                        Command command = (Command) in.readObject();
                        System.out.println("Command: " + command);
                        switch (command){
                            case ECHO:
                                String message = (String) in.readObject();
                                out.writeObject(message);
                                break;

                            case CREATE:
                                Student student = (Student) in.readObject();
                                store.add(student);
                                break;

                            case GET:
                               int id = (Integer) in.readObject();
                               Student resStudent = store.get(id);
                               out.writeObject(resStudent);
                               break;

                            case FILTER:
                                Filter filter = (Filter) in.readObject();
                                List<Student> students = store.filter(filter);
                                out.writeObject(students);
                                break;
                        }

                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
