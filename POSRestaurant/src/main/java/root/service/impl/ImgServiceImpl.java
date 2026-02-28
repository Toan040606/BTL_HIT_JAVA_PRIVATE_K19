package root.service.impl;

import javafx.scene.control.Button;
import lombok.Getter;
import root.service.ImgService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class ImgServiceImpl implements ImgService {
    Path choosingFilePath;
    @Override
    public void choosingFilePath(File file, Button button) {
        if (file == null) {
          return;
        }
        button.setText(file.getName());
        choosingFilePath = file.toPath();
    }

    @Override
    public Path getChoosingFilePath() {
        return choosingFilePath;
    }

    @Override
    public void copyFile(File file) throws IOException {
        if (file != null) {
            String OrgPath = "D:\\Code\\BTL_Java_Private_K19\\POSRestaurant\\src\\main\\resources\\assets\\";
            Path filePath = file.toPath();
            Path targetPath = Path.of(OrgPath + file.getName());

            Files.copy(filePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
            choosingFilePath = targetPath;
            System.out.println("gud");
        }
    }
}
