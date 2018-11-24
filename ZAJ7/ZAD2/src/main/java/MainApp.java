import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;


    private MainAppController mainAppController;
    private BrowserController browserController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        primaryStage.setTitle("ImageBrowser");

        initializeRootLayout();
        initializeImageBrowser();
    }

    private void initializeRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("RootLayout.fxml"));
            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();


            mainAppController = loader.getController();
            mainAppController.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeImageBrowser() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("ImageBrowser.fxml"));
            ScrollPane scrollPane = loader.load();
            scrollPane.setFitToWidth(true);
            rootLayout.setCenter(scrollPane);


            browserController = loader.getController();
            browserController.setMainApp(this);
            mainAppController.setBrowserController(browserController);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
