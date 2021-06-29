package rpc;

import shared.StudentDao;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class StubFactory<T> {
    private final Class<T> cls;

    public StubFactory(Class<T> cls) {
        this.cls = cls;
    }

    public T create(String address, int port) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        String source = generateClientSourceCode();
        File root = new File("/tmp");
        File sourceFile = new File(root, "stub/Client.java");
        sourceFile.getParentFile().mkdirs();
        Files.write(sourceFile.toPath(), source.getBytes(StandardCharsets.UTF_8));
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, System.out, System.err, sourceFile.getPath());
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { root.toURI().toURL() });
        Class<?> cls = Class.forName("stub.Client", true, classLoader);
        T ret = (T) cls.getDeclaredConstructor().newInstance();
        ((SocketClient) ret).connect(address, port);
        return ret;
    }

    private String generateClientSourceCode() {
        StringBuilder b = new StringBuilder();
        b.append("package stub;\n");

        b.append("import java.io.*;\n");
        b.append("import java.net.Socket;\n");

        b.append("public class Client extends rpc.SocketClient implements %s {\n");
        b.append("  public Client() {}\n");
        b.append("%s\n");
        b.append("}\n");
        String source = String.format(b.toString(), cls.getName(), generateMethods());
        return source.toString();
    }

    private String generateMethods() {
        StringBuilder ret = new StringBuilder();
        for (Method m : cls.getDeclaredMethods()) {
            if (!m.isAnnotationPresent(RPCMethod.class)) {
                continue;
            }
            ret.append(String.format("  public %s %s(", m.getReturnType().getName(), m.getName()));
            for (Parameter p : m.getParameters()) {
                ret.append(String.format("%s %s, ", p.getType().getName(), p.getName()));
            }
            if (m.getParameterCount() > 0) {
                ret.delete(ret.length() - 2, ret.length());
            }
            ret.append(") {\n");
            ret.append("    try {\n");
            ret.append(String.format("    out.writeObject(\"%s\");\n", m.getName()));
            for (Parameter p : m.getParameters()) {
                ret.append(String.format("    out.writeObject(%s);\n", p.getName()));
            }
            ret.append("    out.flush();\n");
            ret.append(String.format("    return (%s) inp.readObject();\n", m.getReturnType().getName()));
            ret.append("    } catch (IOException | ClassNotFoundException e) {\n");
            ret.append("      e.printStackTrace();\n");
            ret.append("    }\n");
            ret.append("    return null;\n");
            ret.append("  }\n\n");
        }
        return ret.toString();
    }

//    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
//        StubFactory<StudentDao> f = new StubFactory<>(StudentDao.class);
//        StudentDao client = f.create("localhost", 8081);
//    }
}
