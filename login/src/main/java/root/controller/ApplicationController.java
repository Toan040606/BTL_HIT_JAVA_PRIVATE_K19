package root.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.TilePane;
import root.service.TableService;
import root.service.impl.TableServiceImpl;


public class ApplicationController {
    TableService tableService = new TableServiceImpl();

    @FXML
    private TilePane tableList;

    @FXML
    void initialize() {
        tableService.showTable(tableList);
    }
}
