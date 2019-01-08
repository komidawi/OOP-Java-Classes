import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MessengerApp extends Application {

    private BorderPane rootLayout;
    private Stage primaryStage;
    private ChatWindowController controller;
    private MessengerClient client;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initializeLayout(primaryStage);
        initializeClient();
    }

    private void initializeClient() {
        try {
            client = new MessengerClient("localhost", 3000);
            client.setupConnection();
            client.setController(controller);
            controller.setClient(client);
        } catch (IOException e) {
            showErrorPopup(e);
        }
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

            controller = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializePrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Simple Chat");
    }

    private void showErrorPopup(Exception e) {
        Alert connectionFailed = new Alert(Alert.AlertType.ERROR);
        connectionFailed.setTitle("Connection error.");
        connectionFailed.setHeaderText("Failed to connect with the server.");
        connectionFailed.setContentText(e.toString());
        connectionFailed.showAndWait();
    }

    @Override
    public void stop() throws Exception {
        client.closeConnection();
    }
}
