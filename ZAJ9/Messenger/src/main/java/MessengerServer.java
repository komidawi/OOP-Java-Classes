import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class MessengerServer {

    public static final int PORT_NUMBER = 3000;
    private ServerSocket serverSocket;
    private final Map<Socket, PrintWriter> outputs = Collections.synchronizedMap(new HashMap<>());

    public MessengerServer(int portNumber) {
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new MessengerServer(PORT_NUMBER).go();
    }

    private void go() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());

                synchronized (outputs) {
                    outputs.put(clientSocket, writer);
                }

                new Thread(new ConnectionHandler(clientSocket, this)).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessageToEveryone(String message) {
        synchronized (outputs) {
            for (Map.Entry<Socket, PrintWriter> entry : outputs.entrySet()) {
                entry.getValue().println(message);
                entry.getValue().flush();
            }
        }
    }

    public void closeConnection(Socket clientSocket) {
        synchronized (outputs) {
            outputs.remove(clientSocket);
        }
    }
}
