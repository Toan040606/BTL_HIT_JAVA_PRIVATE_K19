package root.controller.components;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FoodItemController {
    @FXML
    private Button deleteFood;

    @FXML
    private Button editFood;

    @FXML
    private ImageView img;

    @FXML
    private Label foodName;

    public void setData(String name, String imgPath) {
        foodName.setText(name);
        img.setImage(new Image("file:/" + imgPath.replace("\\", "/")));
    }
}
