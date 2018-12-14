import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MessengerClient extends Application {
    private String hostIP = "localhost";
    private int hostPort = 3000;
    private BufferedReader socketReader;
    private PrintWriter socketWriter;
    private BorderPane rootLayout;
    private Stage primaryStage;
    private ChatWindowController controller;

    // TODO: Exception handling
    // TODO: split into smaller pieces


    @Override
    public void start(Stage primaryStage) throws Exception {
        initializeLayout(primaryStage);
        setupConnection();

        //closeConnection();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void initializeLayout(Stage primaryStage) {
        try {
            initializePrimaryStage(primaryStage);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MessengerClient.class.getResource("ChatWindow.fxml"));
            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

            initializeController(loader);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void initializePrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Simple Chat");
    }

    private void initializeController(FXMLLoader loader) {
        controller = loader.getController();
        controller.setClient(this);
    }

    private void setupConnection() {
        try {
            Socket client = new Socket(hostIP, hostPort);
            socketReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            socketWriter = new PrintWriter(client.getOutputStream());
            new Thread(new IncomingMessagesReader()).start();
        } catch (UnknownHostException e) {
            System.out.println("Error setting up socket connection: unknown host at " + hostIP + ":" + hostPort);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error setting up socket connection: " + e);
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        socketWriter.println(message);
        socketWriter.flush();
    }

    private void closeConnection() {
        try {
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
                    System.out.println("Client read: " + message);
                    controller.readMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
