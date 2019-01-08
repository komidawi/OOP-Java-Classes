import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {
    private ServerSocket serverSocket;
    private final List<Socket> outputs = Collections.synchronizedList(new ArrayList<>());

    public Server(int portNumber) {
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server(3000).go();
    }

    private void go() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();

                new Thread(new ConnectionHandler(clientSocket, this)).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeConnection(Socket clientSocket) {
        synchronized (outputs) {
            outputs.remove(clientSocket);
        }
    }

    public void update(String message) {

    }
}
