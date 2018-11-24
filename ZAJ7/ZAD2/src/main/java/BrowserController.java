import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;

public class BrowserController extends ScrollPane {

    @FXML
    private TilePane mainPane;

    @FXML
    private ScrollPane scrollPane;

    private Stage stage;
    private MainApp mainApp;

    private final int HEIGHT_SCALE = 7;
    private final int WIDTH_SCALE = 6;

    private int thumbnailHeight;
    private int thumbnailWidth;

    private EventHandler<MouseEvent> eventHandler;


    public BrowserController() {
        determineThumbnailSize();

        eventHandler = new DisplayFullSizeImageHandler();
    }

    public void determineThumbnailSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        thumbnailHeight = screenSize.height / HEIGHT_SCALE;
        thumbnailWidth = screenSize.width / WIDTH_SCALE;
    }

    public void createImages(File selectedDirectory) {
        //TODO: It can be done using JAVA 8 walk (see how)
        //TODO: take care of closing streams

        mainPane.setVgap(10);
        mainPane.setHgap(10);
        mainPane.setPadding(new Insets(10, 10, 10, 10));

        try {
            File[] files = selectedDirectory.listFiles(new ImageFilter());

            ObservableList list = mainPane.getChildren();
            list.clear();

            double a = mainPane.getPrefTileWidth();
            double b = mainPane.getPrefTileHeight();

            for (File file : files) {
                FileInputStream inputStream = new FileInputStream(file);
                Image image = new Image(inputStream, a, b, true, true); //, thumbnailWidth, thumbnailHeight, true, true);
                FileImageView imageView = new FileImageView(image, file);
                imageView.setPreserveRatio(true);
                imageView.setOnMouseClicked(eventHandler);

                list.add(imageView);
            }
        } catch (Exception e) {

        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }


    private class DisplayFullSizeImageHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            try {
                FileImageView imageView = (FileImageView) event.getSource();
                File imageFile = imageView.getFile();
                FileInputStream inputStream = new FileInputStream(imageFile);
                Image image = new Image(inputStream);
                ImageView imageView1 = new ImageView(image);
                imageView1.setPreserveRatio(true);
                imageView1.setSmooth(true);

                BorderPane borderPane = new BorderPane(imageView1);

                Stage stage = new Stage();
                stage.setMaximized(true);
                Scene scene = new Scene(borderPane);
                imageView1.fitWidthProperty().bind(scene.widthProperty());
                imageView1.fitHeightProperty().bind(scene.heightProperty());
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
