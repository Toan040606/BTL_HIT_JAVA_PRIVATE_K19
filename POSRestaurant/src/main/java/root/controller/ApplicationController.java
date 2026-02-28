package root.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import root.controller.components.FoodItemController;
import root.dao.AreaDao;
import root.dao.FoodCategoryDao;
import root.dao.impl.AreaDaoImpl;
import root.dao.impl.FoodCategoryDaoImpl;
import root.model.entity.core.Area;
import root.model.entity.menu.FoodCategory;
import root.service.*;
import root.service.impl.*;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class ApplicationController {
    Scanner scanner = new Scanner(System.in);
    AreaService areaService;
    TableService tableService;
    AreaDao areaDao;
    FoodCategoryService foodCategoryService;
    FileChooser fileChooser;
    ImgServiceImpl imgService;
    FoodService foodService;
    FoodCategoryDao  foodCategoryDao;

    @FXML
    private StackPane container;

    @FXML
    private ToggleButton allTable;

    @FXML
    private BorderPane areaManager;

    @FXML
    private HBox areaManagerBtn;

    @FXML
    private ToggleButton bookedTable;

    @FXML
    private MenuButton chooseArea;

    @FXML
    private MenuButton chooseCategory;

    @FXML
    private Button closeCreateAreaBtn;

    @FXML
    private Button closeCreateFoodCategoryBtn;

    @FXML
    private Button closeCreateTableBtn;

    @FXML
    private Button closeCreateFoodBtn;

    @FXML
    private Button confirmCreateAreaBtn;

    @FXML
    private Button confirmCreateFoodCategoryBtn;

    @FXML
    private Button confirmCreateFoodInfoBtn;

    @FXML
    private Button confirmCreateTableInfoBtn;

    @FXML
    private Button createAreaBtn;

    @FXML
    private TextField createAreaName;

    @FXML
    private BorderPane createAreaPopup;

    @FXML
    private Button createFoodBtn;

    @FXML
    private Button createFoodCategoryBtn;

    @FXML
    private TextField createFoodCategoryName;

    @FXML
    private BorderPane createFoodCategoryPopup;

    @FXML
    private TextArea createFoodDesField;

    @FXML
    private TextField createFoodNameField;

    @FXML
    private BorderPane createFoodPopup;

    @FXML
    private TextField createFoodPriceField;

    @FXML
    private TextField createSortOrderFoodCategory;

    @FXML
    private Button createTableBtn;

    @FXML
    private TextField createTableNameField;

    @FXML
    private BorderPane createTablePopup;

    @FXML
    private TextField createTableSeatField;

    @FXML
    private Button deleteAreaBtn;

    @FXML
    private Button deleteFoodCategoryBtn;

    @FXML
    private Button editFoodCategoryBtn;

    @FXML
    private Accordion foodCategory;

    @FXML
    private BorderPane foodManager;

    @FXML
    private HBox foodManagerBtn;

    @FXML
    private Button goConfigurationBtn;

    @FXML
    private Button goTablePageBtn;

    @FXML
    private ImageView logoutBtn;

    @FXML
    private BorderPane restaurantPage;

    @FXML
    private TabPane tabPaneArea;

    @FXML
    private TilePane tableList;

    @FXML
    private BorderPane tablePage;

    @FXML
    private Button uploadImgBtn;

    @FXML
    void initialize() {
        areaService = new AreaServiceImpl();
        tableService = new TableServiceImpl();
        areaDao = new AreaDaoImpl();
        foodCategoryService = new FoodCategoryServiceImpl();
        imgService = new ImgServiceImpl();
        foodService = new FoodServiceImpl();
        foodCategoryDao = new FoodCategoryDaoImpl();

        restaurantPage.setVisible(false);
        tablePage.setVisible(false);
        createAreaPopup.setManaged(false);
        createTablePopup.setManaged(false);
    }

    @FXML
    void goConfiguration(ActionEvent event) {
        restaurantPage.setVisible(true);
        tablePage.setVisible(false);

        tableList.getChildren().clear();
    }

    @FXML
    void goTablePage(ActionEvent event) throws IOException {
        restaurantPage.setVisible(false);
        tablePage.setVisible(true);

        tableList.getChildren().clear();
        tableService.showTableOnTablePage(tableList, true);
    }

    @FXML
    void tableFilter(ActionEvent event) throws IOException {
        if (event.getSource() == bookedTable) {
            tableList.getChildren().clear();
            tableService.showTableOnTablePage(tableList, false);
        } else if (event.getSource() == allTable) {
            tableList.getChildren().clear();
            tableService.showTableOnTablePage(tableList, true);
        }
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
        chooseArea.getItems().clear();
        createTablePopup.setVisible(false);
        createTablePopup.setManaged(false);
    }


    @FXML
    void confirmCreateArea(ActionEvent event) {
        String areaName = createAreaName.getText();

        areaService.createArea(areaName);

        createAreaName.clear();
        createAreaPopup.setVisible(false);
        createAreaPopup.setManaged(false);
    }

    @FXML
    void confirmCreateTableInfo(ActionEvent event) {
        String tableName = createTableNameField.getText();
        int seats = Integer.parseInt(createTableSeatField.getText());

        tableService.createTable(tableName, seats);

        chooseArea.getItems().clear();
        createTablePopup.setVisible(false);
        createTablePopup.setManaged(false);
    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        UserAuth userAuth = new UserAuthImpl();
        userAuth.logout(logoutBtn);
    }

    @FXML
    void callDeleteArea(ActionEvent event) {

    }

    @FXML
    void goFoodManager(MouseEvent event) throws IOException {
        foodManager.setVisible(true);
        foodManager.setManaged(true);
        areaManager.setManaged(false);
        areaManager.setVisible(false);

        foodCategory.getPanes().clear();
        foodCategoryService.showCategoryOnFoodManager(foodCategory);
    }

    @FXML
    void goAreaManager(MouseEvent event) throws IOException {
        areaManager.setVisible(true);
        areaManager.setManaged(true);
        foodManager.setManaged(false);
        foodManager.setVisible(false);

        tabPaneArea.getTabs().clear();
        tableService.showTableOnConfigurationPage(tabPaneArea);
    }

    @FXML
    void callCreateFoodCategory(ActionEvent event) {
        createFoodCategoryPopup.setVisible(true);
        createFoodCategoryPopup.setManaged(true);
    }

    @FXML
    void closeCreateFoodCategoryPopup(ActionEvent event) {
        createFoodCategoryPopup.setVisible(false);
        createFoodCategoryPopup.setManaged(false);

        createFoodCategoryName.clear();
        createSortOrderFoodCategory.clear();
    }

    @FXML
    void confirmCreateFoodCategory(ActionEvent event) throws IOException {
        String name = createFoodCategoryName.getText();
        String sortOrderFoodCategory = createSortOrderFoodCategory.getText();

        foodCategoryService.createFoodCategory(name, Integer.parseInt(sortOrderFoodCategory), true);
        foodCategory.getPanes().clear();
        foodCategoryService.showCategoryOnFoodManager(foodCategory);
        createSortOrderFoodCategory.clear();
        createFoodCategoryName.clear();
        createFoodCategoryPopup.setVisible(false);
        createFoodCategoryPopup.setManaged(false);
    }

    @FXML
    void callEditFoodCategory(ActionEvent event) {

    }

    @FXML
    void callCreateFood(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CreateFoodPopup.fxml"));
        Parent item = loader.load();

        CreateFoodPopup controller = loader.getController();
        controller.setParentStackpane(container);
        container.getChildren().add(item);
        controller.setCategoryAccordion(foodCategory);
    }
}
