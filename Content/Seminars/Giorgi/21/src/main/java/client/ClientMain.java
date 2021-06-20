package client;

import shared.*;

import java.io.IOException;
import java.util.List;

public class ClientMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        EchoClient client = new EchoClient("localhost", 8081);
        client.connect();
        System.out.println("CLIENT: " + client.echo("foo"));
        System.out.println("CLIENT: " + client.echo("bar"));
        Student free = new Student("Free", "Uni", 2021);
        client.createStudent(free);
        Student foo = new Student("Foo", "Uni", 2020);
        client.createStudent(foo);
        printStudents(client.filterStudents(new FirstNameFilter("Free")));
        printStudents(client.filterStudents(
                new AndFilter()
                .add(new LastNameFilter("Uni"))
                .add(new EnrollmentYearFilter(2020))));
        client.disconnect();
    }

    private static void printStudents(List<Student> students) {
        System.out.println("-----");
        for (Student st : students) {
            System.out.println(st);
        }
        System.out.println("-----");
    }
}
