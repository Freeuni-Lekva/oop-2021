package server;

import shared.ArrayListStudentDao;
import shared.StudentDao;

import java.io.IOException;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        StudentDao students = new ArrayListStudentDao();
        EchoServer server = new EchoServer(8081, students);
        server.start();
    }
}
