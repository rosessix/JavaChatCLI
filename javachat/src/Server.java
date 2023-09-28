import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class Server {
    private static final int PORT = 8080;
    private static Set<PrintWriter> writers = new HashSet<>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server is running on port " + PORT);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("New client connected");
            new Thread(new ClientHandler(clientSocket)).start();

        }
    }

    private static class ClientHandler implements Runnable {
        private Socket clientSocket;
        private PrintWriter out;
        private String username;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                writers.add(out);

                this.username = in.readLine();

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("User " + this.username + " says: " + inputLine);
                    for (PrintWriter writer : writers) {
                        writer.println(this.username + ": " + inputLine);
                    }
                }
            } catch (IOException e) {
                System.out.println("Client disconnected");
            } finally {
                if (out != null) {
                    writers.remove(out);
                }
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
