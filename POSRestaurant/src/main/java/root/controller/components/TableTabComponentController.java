package root.controller.components;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TableTabComponentController {

    @FXML
    private Button deleteTable;

    @FXML
    private Label tableTabName;

    public void setData(String name) {
        tableTabName.setText(name);
    }
}
