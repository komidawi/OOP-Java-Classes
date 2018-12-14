import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MessengerServer {

    private ServerSocket serverSocket;
    private List<PrintWriter> outputStreams = new ArrayList<>();

    public MessengerServer(int portNumber) {
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MessengerServer server = new MessengerServer(3000);
        server.go();
    }

    private void go() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                outputStreams.add(writer);

                new Thread(new ConnectionHandler(clientSocket, this)).start();
                System.out.println("Connection established");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessageToEveryone(String message) {
        for (PrintWriter outputStream : outputStreams) {
            outputStream.println(message);
            outputStream.flush();
        }
    }
}
