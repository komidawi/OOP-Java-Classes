import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Book> bookData = FXCollections.observableArrayList();
    private BookDatabaseUtils databaseUtils = new BookDatabaseUtils();
    private BookOverviewController controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        initializePrimaryStage(primaryStage);
        initializeRootLayout();
        initializeBookOverview();

        updateBookData();
    }

    private void initializePrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Books Database Tools");
    }

    public void updateBookData() {
        if (!databaseUtils.isConnected()) {
            attemptToConnect();
        }

        if (databaseUtils.isConnected()) {
            fetchDataFromDatabase();
        }
    }

    private void fetchDataFromDatabase() {
        List<Book> books = databaseUtils.fetchBookData();
        bookData.clear();
        bookData.addAll(books);
    }

    private void attemptToConnect() {
        int maxConnectionAttempts = 3;
        boolean connected;
        for (int i = 0; i < maxConnectionAttempts; i++) {
            connected = databaseUtils.connect();

            if (connected) {
                return;
            }
        }
        showErrorPopup();
    }

    private void showErrorPopup() {
        Alert connectionFailed = new Alert(Alert.AlertType.ERROR);
        connectionFailed.setTitle("Connection error.");
        connectionFailed.setHeaderText("Failed to connect with database.");
        connectionFailed.setContentText("Please check your internet connection and try again");

        connectionFailed.showAndWait();
    }

    private void initializeRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("RootLayout.fxml"));
            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeBookOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("BookOverview.fxml"));
            TabPane tabPane = loader.load();

            rootLayout.setCenter(tabPane);

            initializeController(loader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeController(FXMLLoader loader) {
        controller = loader.getController();
        controller.setMainApp(this);
        controller.setDatabaseUtils(databaseUtils);
        controller.initializeSearchEngine();
    }

    public ObservableList<Book> getBookData() {
        return bookData;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
