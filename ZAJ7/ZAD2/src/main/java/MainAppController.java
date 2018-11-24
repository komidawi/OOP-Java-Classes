import javafx.fxml.FXML;
import javafx.stage.DirectoryChooser;

import java.io.File;

public class MainAppController {

    private MainApp mainApp;
    private BrowserController browserController;

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
        File selectedDirectory = directoryChooser.showDialog(mainApp.getPrimaryStage());

        browserController.loadThumbnails(selectedDirectory);
    }

}
