import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class FileImageView extends ImageView {
    private File file;

    public FileImageView(Image image, File file) {
        super(image);
        this.file = file;
    }

    public File getFile() {
        return file;
    }
}
