import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class ImageViewerController extends BorderPane {

    private Stage stage;
    private MainApp mainApp;
    private BrowserController browserController;


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setBrowserController(BrowserController browserController) {
        this.browserController = browserController;
    }

    @FXML
    private void openFolder() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Image Folder");
        File defaultDirectory = new File(".");
        directoryChooser.setInitialDirectory(defaultDirectory);
        File selectedDirectory = directoryChooser.showDialog(stage);

        browserController.createImages(selectedDirectory);
    }

}
