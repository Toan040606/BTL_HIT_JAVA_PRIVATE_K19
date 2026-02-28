package root.controller.components;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.FlowPane;

public class TableTabController {

    @FXML
    private ScrollPane scrollTable;

    @FXML
    private FlowPane showHorizonTable;

    @FXML
    private Tab tabId;


    public void setData(String areaName, FlowPane tableList){
        tabId.setText(areaName);
        scrollTable.setContent(tableList);
    }
}
