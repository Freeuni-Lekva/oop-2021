public class Main {

    public static void main(String[] args) {
        int port = 12345;
        Server server = new Server(port);
        Client client = new Client(port);

        server.start();
        client.start();
    }
}
