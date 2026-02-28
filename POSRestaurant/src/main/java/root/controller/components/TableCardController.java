package root.controller.components;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TableCardController {

    @FXML
    private Label seats;

    @FXML
    private Label tableName;

    @FXML
    private Label areaName;

    @FXML
    private Label tableStatus;

    @FXML
    private Label tableTime;

    public void setData(int seat, String areaaName, String tableNames, String tableStatuss) {
        seats.setText(String.valueOf(seat));
        areaName.setText(areaaName);
        tableName.setText(tableNames);
        tableStatus.setText(tableStatuss);
    }
}
