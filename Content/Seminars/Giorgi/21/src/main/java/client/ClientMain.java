package client;

import rpc.StubFactory;
import shared.*;
import shared.filter.descriptor.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ClientMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        // EchoClient client = new EchoClient("localhost", 8081);
        StudentDao client = new StubFactory<StudentDao>(StudentDao.class).create("localhost", 8081);
        // client.connect();
        // System.out.println("CLIENT: " + client.echo("foo"));
        // System.out.println("CLIENT: " + client.echo("bar"));
        Student free = new Student("Free", "Uni", 2021);
        // client.add(free);
        Student foo = new Student("Foo", "Uni", 2020);
        // client.add(foo);
        printStudents(Arrays.asList(client.get(24)));
        printStudents(client.filter(
                new StringCompareOperationDescription(
                        CompareOperator.EQUALS,
                        new FieldValueDescription<>("firstName"),
                        new StringValueDescription("Free"))));
        printStudents(client.filter(
                new AndExpressionDescription()
                .add(new StringCompareOperationDescription(
                        CompareOperator.EQUALS,
                        new FieldValueDescription<>("lastName"),
                        new StringValueDescription("Uni")))
                .add(new IntegerCompareOperationDescription(
                        CompareOperator.LESS_THEN,
                        new FieldValueDescription<>("enrollmentYear"),
                        new IntegerValueDescription(124)))));
        // client.disconnect();
    }

    private static void printStudents(List<Student> students) {
        System.out.println("-----");
        for (Student st : students) {
            System.out.println(st);
        }
        System.out.println("-----");
    }
}
