import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * Realizacja wersji rozszerzonej o wielowątkowość oraz protokół "rozmyty" (podpunkty 1 i 2)
 * @author Czarkowska
 * @author Poleszak
 */
public class Client {
    private final Socket socket;
    private final BufferedReader input;
    private final BufferedWriter output;

    public Client(String address, int port) throws IOException {
        this.socket = new Socket(address, port);
        this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        Thread readingThread = new Thread(() -> {
            String text;
            while (this.socket.isConnected()) {
                try {
                    text = this.input.readLine();
                    System.out.println(text);
                } catch (Exception e) {
                    System.out.println("Client (Client): " + e.getMessage());
                }
            }
        });
        readingThread.start();

        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();
        while (!message.equals("exit")) { //do poprawy zamykanie
            if (!socket.isConnected())
                throw new SocketException("Socket is not connected");
            if ((message.startsWith("get ") && message.split(" ").length == 2)
            || (message.startsWith("put ") && message.split(" ").length == 3)) {
                this.output.write(message);
                this.output.newLine();
                this.output.flush();
            }
            else System.out.println("Wrong input, try again");
            message = scanner.nextLine();
        }
        input.close();
        output.close();
        socket.close();
    }

    public static void main(String[] args) {
        try {
            Client client = new Client("localhost", 9090);
        } catch (Exception e) {
            System.out.println("Client (main): " + e.getMessage());
        }
    }
}
