package server;

import rpc.RPCMethod;
import shared.Command;
import shared.filter.reflection.Filter;
import shared.Student;
import shared.StudentDao;
import shared.filter.reflection.ExpressionBuilder;
import shared.filter.descriptor.ExpressionDescription;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.ServerSocket;
import java.net.Socket;

// Remote Procedure Call
public class RPCServer<T> {
    private final int port;
    private final T impl;
    private final Class<T> cls;

    public RPCServer(int port, T impl, Class<T> cls) {
        this.port = port;
        this.impl = impl;
        this.cls = cls;
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
                    String methodName = (String) inp.readObject();
                    Method method = null;
                    for (Method m : cls.getDeclaredMethods()) {
                        if (m.getName().equals(methodName)) {
                            method = m;
                            break;
                        }
                    }
                    assert(method != null);
                    if (!method.isAnnotationPresent(RPCMethod.class)) {
                        continue; // TODO: return error
                    }
                    Object[] params = new Object[method.getParameterCount()];
                    for (int i = 0; i < method.getParameterCount(); i++) {
                        params[i] = inp.readObject();
                    }
                    Object ret = method.invoke(impl, params);
                    out.writeObject(ret);
                    out.flush();
                }
            } catch (IOException | ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void shutdown() {

    }

}
