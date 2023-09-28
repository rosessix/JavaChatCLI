import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        final String HOST = "localhost";
        final int PORT = 8080;

        Socket socket = new Socket(HOST, PORT);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        Scanner input = new Scanner(System.in);

        // Start a new thread to read incoming messages
        System.out.println("Indtast dit brugernavn højtelskede løvehjerte: ");
        String username = input.nextLine();
        out.println(username);
        System.out.println("For at kunne kommunikere med de andre i chatten, skal du blot skrive din besked og trykke på ENTER.");
        new Thread(() -> {
            try {
                String serverMessage;
                while ((serverMessage = in.readLine()) != null) {
                    System.out.println(serverMessage);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        while (true) {
            String msg = input.nextLine();

            out.println(msg);
        }
    }
}