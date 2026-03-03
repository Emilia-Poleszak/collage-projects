import java.io.*;
import java.net.Socket;
import java.util.HashMap;

/**
 * Realizacja wersji rozszerzonej o wielowątkowość oraz protokół "rozmyty" (podpunkty 1 i 2)
 * @author Czarkowska
 * @author Poleszak
 */
public class ClientHandler implements Runnable {
    private Socket socket;
    private BufferedReader input;
    private BufferedWriter output;
    private HashMap<String,String> phonebook;

    public ClientHandler(Socket socket, HashMap<String,String> phonebook) {
        try {
            this.socket = socket;
            this.phonebook = phonebook;
            this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            System.out.println("ClientHandler (ClientHandler): " + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            String message, name, number;
            while (socket.isConnected()) {
                message = this.input.readLine();
                if (message.startsWith("put ")) {
                    name = message.split(" ")[1];
                    number = message.split(" ")[2];
                    this.phonebook.put(name, number);
                }
                if (message.startsWith("get ")) {
                    name = message.split(" ")[1];
                    boolean found = false;
                    for (String key : this.phonebook.keySet()) {
                        if (key.startsWith(name)) {
                            found = true;
                            this.output.write(key + " " + this.phonebook.get(key));
                            this.output.newLine();
                            this.output.flush();
                        }
                    }
                    if (!found) {
                        this.output.write("--- no name: " + name + " ---");
                        this.output.newLine();
                        this.output.flush();
                    }
                }
            }
        } catch (Exception e) {
            this.closeConnection();
        }
    }

    public void closeConnection() {
        try {
            this.input.close();
            this.output.close();
            this.socket.close();
        } catch (IOException e) {
            System.out.println("ClientHandler (closeConnection): " + e.getMessage());
        }
    }
}
