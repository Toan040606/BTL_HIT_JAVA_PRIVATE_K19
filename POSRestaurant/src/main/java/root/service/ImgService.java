package root.service;

import javafx.scene.control.Button;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public interface ImgService {
    void choosingFilePath(File file, Button button);
    Path getChoosingFilePath();
    void copyFile(File file) throws IOException;
}
