import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ImageViewer extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ImageViewerController imageViewerController;
    private BrowserController browserController;

    public static void main(String[] args) {
        launch(args);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        primaryStage.setTitle("ImageBrowser");

        initializeRootLayout();
        initializeView();
    }

    private void initializeRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ImageViewer.class.getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            imageViewerController = loader.getController();
            imageViewerController.setStage(primaryStage);
            imageViewerController.setImageViewer(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ImageViewer.class.getResource("Browser.fxml"));
            ScrollPane scrollPane = (ScrollPane) loader.load();
            scrollPane.setFitToWidth(true);
            rootLayout.setCenter(scrollPane);

            browserController = loader.getController();
            browserController.setImageViewer(this);

            imageViewerController.setBrowserController(browserController);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
