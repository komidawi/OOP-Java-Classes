import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MessengerClient {
    private String hostIP;
    private int hostPort;
    private BufferedReader socketReader;
    private PrintWriter socketWriter;
    private ChatWindowController controller;

    public MessengerClient(String hostIP, int hostPort) {
        this.hostIP = hostIP;
        this.hostPort = hostPort;
    }

    public void setController(ChatWindowController controller) {
        this.controller = controller;
    }

    public void setupConnection() throws IOException {
        Socket client = new Socket(hostIP, hostPort);
        socketReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        socketWriter = new PrintWriter(client.getOutputStream());
        new Thread(new IncomingMessagesReader()).start();
    }

    public void sendMessage(String message) {
        socketWriter.println(message);
        socketWriter.flush();
    }

    public void closeConnection() {
        try {
            System.out.println("Connection closed");
            socketWriter.close();
            socketReader.close();
        } catch (IOException e) {
            System.out.println("Error tearing down socket connection: " + e);
            e.printStackTrace();
        }
    }


    private class IncomingMessagesReader implements Runnable {
        @Override
        public void run() {
            String message;

            try {
                while ((message = socketReader.readLine()) != null) {
                    controller.readMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
