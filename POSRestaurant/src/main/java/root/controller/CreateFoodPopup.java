package root.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import root.dao.FoodCategoryDao;
import root.dao.FoodDao;
import root.dao.OptionRequiredGroupDao;
import root.dao.impl.FoodCategoryDaoImpl;
import root.dao.impl.FoodDaoImpl;
import root.dao.impl.OptionRequiredGroupDaoImpl;
import root.model.entity.menu.Food;
import root.model.entity.menu.FoodCategory;
import root.model.entity.menu.OptionRequiredGroup;
import root.service.FoodCategoryService;
import root.service.FoodService;
import root.service.ImgService;
import root.service.impl.FoodCategoryServiceImpl;
import root.service.impl.FoodServiceImpl;
import root.service.impl.ImgServiceImpl;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CreateFoodPopup {
    FileChooser fileChooser;
    ImgService imgService;
    FoodService foodService;
    FoodCategoryDao foodCategoryDao;
    FoodCategoryService foodCategoryService;
    FoodDao foodDao;
    OptionRequiredGroupDao optionRequiredGroupDao;

    private StackPane parentStackPane;
    private Accordion categoryAccordion;

    @FXML
    private MenuButton chooseCategory;

    @FXML
    private MenuButton chooseFood;

    @FXML
    private MenuButton chooseFoodForORG;

    @FXML
    private MenuButton chooseORG;

    @FXML
    private Button closeCreateFoodBtn;

    @FXML
    private Button confirmCreateFoodAddonInfoBtn;

    @FXML
    private Button confirmCreateFoodInfoBtn;

    @FXML
    private Button confirmCreateORGBtn;

    @FXML
    private Button confirmCreateORInfoBtn;

    @FXML
    private TextField createFoodAddonNameField;

    @FXML
    private TextField createFoodAddonPriceField;

    @FXML
    private TextArea createFoodDesField;

    @FXML
    private TextField createFoodNameField;

    @FXML
    private BorderPane createFoodPopup;

    @FXML
    private TextField createFoodPriceField;

    @FXML
    private TextField createORGName;

    @FXML
    private TextField createORNameField;

    @FXML
    private TextField createORPriceField;

    @FXML
    private VBox food;

    @FXML
    private VBox foodAddon;

    @FXML
    private ToggleButton foodAddonBtn;

    @FXML
    private ToggleButton foodBtn;

    @FXML
    private VBox oR;

    @FXML
    private ToggleButton oRBtn;

    @FXML
    private VBox oRG;

    @FXML
    private ToggleButton oRGBtn;

    @FXML
    private Button uploadImgBtn;

    @FXML
    void initialize() {
        imgService = new ImgServiceImpl();
        foodService = new FoodServiceImpl();
        foodCategoryDao = new FoodCategoryDaoImpl();
        foodCategoryService = new FoodCategoryServiceImpl();
        foodDao = new FoodDaoImpl();
        optionRequiredGroupDao = new OptionRequiredGroupDaoImpl();

        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

        ToggleGroup toggleGroup = new ToggleGroup();
        foodBtn.setToggleGroup(toggleGroup);
        foodAddonBtn.setToggleGroup(toggleGroup);
        oRBtn.setToggleGroup(toggleGroup);
        oRGBtn.setToggleGroup(toggleGroup);

        food.setManaged(false);
        foodAddon.setManaged(false);
        oR.setManaged(false);
        oRG.setManaged(false);
    }

    @FXML
    void closeCreateFoodPopup(ActionEvent event) {
        parentStackPane.getChildren().remove(createFoodPopup);
    }

    @FXML
    void confirmCreateFoodAddonInfo(ActionEvent event) throws IOException {
        String name = createFoodAddonNameField.getText();
        double price = Double.parseDouble(createFoodAddonPriceField.getText());

        foodService.createFoodAddon(name, price, true);
        closeAfterCreateFoodPopup();
    }

    @FXML
    void confirmCreateFoodInfo(ActionEvent event) throws IOException {
        imgService.copyFile(imgService.getChoosingFilePath().toFile());
        String imgPath = String.valueOf(imgService.getChoosingFilePath());
        String name = createFoodNameField.getText();
        double price = Double.parseDouble(createFoodPriceField.getText());
        String description = createFoodDesField.getText();

        foodService.createFood(name, imgPath, price, description, true);
        closeAfterCreateFoodPopup();
    }

    @FXML
    void confirmCreateORG(ActionEvent event) throws IOException {
        String name = createORGName.getText();

        foodService.createORG(name, true);
        closeAfterCreateFoodPopup();
    }

    @FXML
    void confirmCreateORInfo(ActionEvent event) throws IOException {
        String name = createORNameField.getText();
        double price = Double.parseDouble(createORPriceField.getText());

        foodService.createOR(name, price);
        closeAfterCreateFoodPopup();
    }

    @FXML
    void createType(ActionEvent event) {
        if (event.getSource() == foodBtn) {
            food.setVisible(true);
            food.setManaged(true);
            foodAddon.setVisible(false);
            foodAddon.setManaged(false);
            oR.setVisible(false);
            oR.setManaged(false);
            oRG.setVisible(false);
            oRG.setManaged(false);

            List<FoodCategory> foodCategories = foodCategoryDao.findAll();

            chooseCategory.getItems().clear();
            foodService.showAllFoodCategory(foodCategories, chooseCategory);
        }
        if (event.getSource() == foodAddonBtn) {
            food.setVisible(false);
            food.setManaged(false);
            foodAddon.setVisible(true);
            foodAddon.setManaged(true);
            oR.setVisible(false);
            oR.setManaged(false);
            oRG.setVisible(false);
            oRG.setManaged(false);

            List<Food> foods = foodDao.findAll();

            chooseFood.getItems().clear();
            foodService.showAllFood(foods, chooseFood);
        }
        if (event.getSource() == oRBtn) {
            food.setVisible(false);
            food.setManaged(false);
            foodAddon.setVisible(false);
            foodAddon.setManaged(false);
            oR.setVisible(true);
            oR.setManaged(true);
            oRG.setVisible(false);
            oRG.setManaged(false);

            List<OptionRequiredGroup> optionRequiredGroups = optionRequiredGroupDao.findAll();

            chooseORG.getItems().clear();
            foodService.showAllORG(optionRequiredGroups, chooseORG);
        }
        if (event.getSource() == oRGBtn) {
            food.setVisible(false);
            food.setManaged(false);
            foodAddon.setVisible(false);
            foodAddon.setManaged(false);
            oR.setVisible(false);
            oR.setManaged(false);
            oRG.setVisible(true);
            oRG.setManaged(true);

            List<Food> foods = foodDao.findAll();

            chooseFoodForORG.getItems().clear();
            foodService.showAllFood(foods, chooseFoodForORG);
        }
    }

    @FXML
    void uploadImg(ActionEvent event) {
        File file = fileChooser.showOpenDialog(new Stage());
        imgService.choosingFilePath(file, uploadImgBtn);
    }

    public void setParentStackpane(StackPane parent) {
        this.parentStackPane = parent;
    }

    public void setCategoryAccordion(Accordion accordion) {
        this.categoryAccordion = accordion;
    }

    private void closeAfterCreateFoodPopup() throws IOException {
        categoryAccordion.getPanes().clear();
        foodCategoryService.showCategoryOnFoodManager(categoryAccordion);
        parentStackPane.getChildren().remove(createFoodPopup);
    }
}
