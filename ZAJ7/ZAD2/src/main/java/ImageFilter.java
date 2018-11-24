import java.io.File;
import java.io.FileFilter;

public class ImageFilter implements FileFilter {

    private final String[] VALID_EXTENSIONS = {"jpg", "png", "gif", "bmp"};

    @Override
    public boolean accept(File file) {
        for (String extension : VALID_EXTENSIONS) {
            if (file.getName().toLowerCase().endsWith(extension)) {
                return true;
            }
        }
        return false;
    }
}
