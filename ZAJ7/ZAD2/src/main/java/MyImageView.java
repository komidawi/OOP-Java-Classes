import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class MyImageView extends ImageView {
    private File imageFile;

    public MyImageView(Image image, File imageFile) {
        super(image);
        this.imageFile = imageFile;
    }

    public File getImageFile() {
        return imageFile;
    }
}
