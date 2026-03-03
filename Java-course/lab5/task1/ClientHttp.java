import java.io.*;
import java.net.*;

/**
 * @author Emilia Poleszak
 * @author Anna Czarkowska
 */

public class ClientHttp {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Invalid input arguments.");
            return;
        }

        String address = args[0];
        int port = Integer.parseInt(args[1]);
        String url = args[2];

        try (Socket socket = new Socket(address, port);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            writer.println("GET " + url + " HTTP/1.1");
            writer.println("Host: " + address);
            writer.println("Connection: close");
            writer.println();

            String response = reader.readLine();
            while (response != null) {
                System.out.println(response);
                response = reader.readLine();
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid input arguments.");
        } catch (IOException e) {
            System.err.println("Connection error.");
        }
    }
}
