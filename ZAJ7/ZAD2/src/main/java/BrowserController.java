import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class BrowserController extends ScrollPane {

    @FXML
    private FlowPane flowPane;

    @FXML
    private ScrollPane scrollPane;

    private Stage stage;
    private ImageViewer imageViewer;

    private final int HEIGHT_SCALE = 5;
    private final int WIDTH_SCALE = 5;

    private int thumbnailHeight;
    private int thumbnailWidth;


    public BrowserController() {
        determineThumbnailSize();
    }

    public FlowPane getFlowPane() {
        return flowPane;
    }

    public void determineThumbnailSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        thumbnailHeight = screenSize.height / HEIGHT_SCALE;
        thumbnailWidth = screenSize.width / WIDTH_SCALE;
    }

    public void createImages(File selectedDirectory) {
        //TODO: It can be done using JAVA 8 walk (see how)
        //TODO: take care of closing streams

        //flowPane.setPrefWrapLength(1000);

        try {
            File[] files = selectedDirectory.listFiles(new ImageFilter());

            ObservableList list = flowPane.getChildren();

            for (int i = 0; i < 1000; i++) {
                list.add(new Button(String.valueOf(i)));
            }

            /*for (File file : files) {
                FileInputStream inputStream = new FileInputStream(file);
                Image image = new Image(inputStream);
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(thumbnailHeight);
                imageView.setFitWidth(thumbnailWidth);
                //imageView.setPreserveRatio(true);
                list.add(imageView);
            }*/
        } catch (Exception e) {

        }
    }

    public void setImageViewer(ImageViewer imageViewer) {
        this.imageViewer = imageViewer;
    }



    public void setStage(Stage stage) {
        this.stage = stage;
    }


}
