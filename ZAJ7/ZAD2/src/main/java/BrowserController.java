import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BrowserController {

    @FXML
    private TilePane imageViewPane;

    private MainApp mainApp;
    private EventHandler<MouseEvent> eventHandler;


    public BrowserController() {
        eventHandler = new ThumbnailClickHandler();
    }

    public void loadThumbnails(File selectedDirectory) {
        //TODO: It can be done using JAVA 8 walk (see how)
        File[] files = selectedDirectory.listFiles(new ImageFilter());

        ObservableList<Node> list = imageViewPane.getChildren();
        list.clear();

        double thumbnailWidth = imageViewPane.getPrefTileWidth();
        double thumbnailHeight = imageViewPane.getPrefTileHeight();

        for (File file : files) {
            try (FileInputStream inputStream = new FileInputStream(file)) {
                Image image = new Image(inputStream, thumbnailWidth, thumbnailHeight, true, true);
                FileImageView thumbnail = new FileImageView(image, file);
                thumbnail.setPreserveRatio(true);
                thumbnail.setOnMouseClicked(eventHandler);

                list.add(thumbnail);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private class ThumbnailClickHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            try {
                ImageView fullScreenImage = createFullSizeImage(event);
                showFullSizeImage(fullScreenImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private ImageView createFullSizeImage(MouseEvent event) throws FileNotFoundException {
            FileImageView clicked = (FileImageView) event.getSource();
            File sourceImage = clicked.getFile();
            FileInputStream inputStream = new FileInputStream(sourceImage);
            Image image = new Image(inputStream);
            ImageView fullScreenView = new ImageView(image);

            fullScreenView.setPreserveRatio(true);
            fullScreenView.setSmooth(true);

            return fullScreenView;
        }

        private void showFullSizeImage(ImageView fullScreenImage) {
            BorderPane borderPane = new BorderPane(fullScreenImage);
            Stage stage = new Stage();
            stage.setMaximized(true);
            Scene scene = new Scene(borderPane);
            fullScreenImage.fitWidthProperty().bind(scene.widthProperty());
            fullScreenImage.fitHeightProperty().bind(scene.heightProperty());
            stage.setScene(scene);
            stage.show();
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
