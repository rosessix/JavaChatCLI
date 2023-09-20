import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        final int PORT = 8080;
        ServerSocket server = new ServerSocket(PORT);

        new Thread(() -> {
            while (true) {
                try {
                    Socket socket = server.accept();
                    UserThread user = new UserThread(socket);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }).start();

    }

    static class UserThread {
        private Socket socket;
        private String userName;
        private BufferedReader br;
        private PrintWriter pw;
        public UserThread(Socket socket) throws IOException {
            this.socket = socket;
            this.br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.pw = new PrintWriter(this.socket.getOutputStream(), true);
            System.out.println("trying to read line?");

            this.userName = this.br.readLine();
            System.out.println(this.userName);

            this.recieveThread(this.br, this.pw);

        }

        private void recieveThread(BufferedReader br, PrintWriter pw) throws IOException {
            String msg;
            while (true) {
                msg = br.readLine();
                if (msg != null)
                    System.out.println("msg " + msg);
            }
        }
    }
}
