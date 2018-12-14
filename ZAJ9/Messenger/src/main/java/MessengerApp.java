import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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

        //closeConnection();
    }

    private void initializeClient() {
        client = new MessengerClient("localhost", 3000);
        client.setupConnection();
        client.setController(controller);
        controller.setClient(client);
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
}
