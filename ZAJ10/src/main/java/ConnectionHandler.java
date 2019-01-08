import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ConnectionHandler implements Runnable {

    private MessengerServer server;
    private Socket socket;
    private BufferedReader reader;

    public ConnectionHandler(Socket socket, MessengerServer server) {
        try {
            this.socket = socket;
            this.server = server;
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String message;
        try {
            while ((message = reader.readLine()) != null) {
                server.sendMessageToEveryone(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            server.closeConnection(socket);
        }
    }
}
