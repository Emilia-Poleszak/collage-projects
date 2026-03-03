import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Realizacja wersji rozszerzonej o wielowątkowość oraz protokół "rozmyty" (podpunkty 1 i 2)
 * @author Czarkowska
 * @author Poleszak
 */
public class Server {
    private ServerSocket serverSocket;
    private final HashMap<String,String> phonebook = new HashMap<>();

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            //System.out.println(Inet4Address.getLocalHost().getHostAddress());
        } catch (IOException e) {
            System.out.println("Server (Server): " + e.getMessage());
        }
    }

    public void start() {
        while (!serverSocket.isClosed()) {
            try {
                Socket socket = serverSocket.accept();
                Thread thread = new Thread(new ClientHandler(socket, phonebook));
                thread.start();
            } catch (IOException e) {
                System.out.println("Server (start): " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server(9090);
        server.start();
    }
}
