package root.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import root.dao.AreaDao;
import root.dao.impl.AreaDaoImpl;
import root.model.entity.core.Area;
import root.service.AreaService;
import root.service.TableService;
import root.service.impl.AreaServiceImpl;
import root.service.impl.TableServiceImpl;

import java.util.List;

public class ApplicationController {
    AreaService areaService = new AreaServiceImpl();
    TableService tableService = new TableServiceImpl();
    AreaDao areaDao = new AreaDaoImpl();

    @FXML
    private Button allTableBtn;

    @FXML
    private HBox areaManagerBtn;

    @FXML
    private Button bookedTableBtn;

    @FXML
    private MenuButton chooseArea;

    @FXML
    private Button closeCreateAreaBtn;

    @FXML
    private Button closeCreateTableBtn;

    @FXML
    private Button confirmCreateAreaBtn;

    @FXML
    private Button confirmCreateTableInfoBtn;

    @FXML
    private Button createAreaBtn;

    @FXML
    private TextField createAreaName;

    @FXML
    private BorderPane createAreaPopup;

    @FXML
    private Button createTableBtn;

    @FXML
    private TextField createTableNameField;

    @FXML
    private BorderPane createTablePopup;

    @FXML
    private TextField createTableSeatField;

    @FXML
    private Button goConfigurationBtn;

    @FXML
    private Button goTablePageBtn;

    @FXML
    private BorderPane restaurantPage;

    @FXML
    private TabPane tabPaneArea;

    @FXML
    private TilePane tableList;

    @FXML
    private BorderPane tablePage;

    @FXML
    void initialize() {
        restaurantPage.setVisible(false);
        tablePage.setVisible(false);
        createAreaPopup.setManaged(false);
        createTablePopup.setManaged(false);
    }

    @FXML
    void goConfiguration(ActionEvent event) {
        restaurantPage.setVisible(true);
        tablePage.setVisible(false);
    }

    @FXML
    void goTablePage(ActionEvent event) {
        restaurantPage.setVisible(false);
        tablePage.setVisible(true);
    }

    @FXML
    void allTable(ActionEvent event) {

    }

    @FXML
    void bookedTable(ActionEvent event) {

    }

    @FXML
    void callCreateArea(ActionEvent event) {
        createAreaPopup.setVisible(true);
        createAreaPopup.setManaged(true);
    }

    @FXML
    void callCreateTable(ActionEvent event) {
        createTablePopup.setVisible(true);
        createTablePopup.setManaged(true);

        List<Area> areas = areaDao.findAll();

        tableService.showChooseAllArea(areas, chooseArea);
    }

    @FXML
    void closeCreateAreaPopup(ActionEvent event) {
        createAreaName.clear();
        createAreaPopup.setVisible(false);
        createAreaPopup.setManaged(false);
    }

    @FXML
    void closeCreateTablePopup(ActionEvent event) {
        createTableNameField.clear();
        createTableSeatField.clear();
        createTablePopup.setVisible(false);
        createTablePopup.setManaged(false);
    }


    @FXML
    void confirmCreateArea(ActionEvent event) {
        String areaName = createAreaName.getText();

        areaService.createArea(areaName);
        createAreaName.clear();
        chooseArea.getItems().clear();
        createAreaPopup.setVisible(false);
        createAreaPopup.setManaged(false);
    }

    @FXML
    void confirmCreateTableInfo(ActionEvent event) {
        String tableName = createTableNameField.getText();
        int seats = Integer.parseInt(createTableSeatField.getText());
        String chosenArea = chooseArea.getText();

    }

}
