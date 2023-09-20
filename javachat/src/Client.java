import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        final int PORT = 8080;
        final String HOST = "localhost";

        Scanner input = new Scanner(System.in);
        System.out.println("Hvad vil du have dit brugernavn skal være?: ");
        String userName = input.next();

        try (Socket socket = new Socket(HOST, PORT)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());

            out.println(userName);
            out.flush();

            sendThread(in, out);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void sendThread(BufferedReader br, PrintWriter pw) {
        Scanner input = new Scanner(System.in);
        new Thread(() -> {
            while (true) {
                System.out.println("Dig: ");
                String msg = input.nextLine();
                System.out.println("Sender besked: " + msg);
                pw.println(msg);
                pw.flush();
            }
        }).start();
    }
}
