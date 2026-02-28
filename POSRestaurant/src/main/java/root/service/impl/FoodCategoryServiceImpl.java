package root.service.impl;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Accordion;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import root.controller.components.FoodItemController;
import root.controller.components.TableTabComponentController;
import root.dao.FoodCategoryDao;
import root.dao.impl.FoodCategoryDaoImpl;
import root.model.entity.menu.Food;
import root.model.entity.menu.FoodCategory;
import root.service.FoodCategoryService;
import root.util.UserSession;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class FoodCategoryServiceImpl implements FoodCategoryService {
    FoodCategoryDao foodCategoryDao = new FoodCategoryDaoImpl();

    @Override
    public void showCategoryOnFoodManager(Accordion accordion) throws IOException {
        List<FoodCategory> foodCategories = foodCategoryDao.findAll();

        foodCategories.sort(Comparator.comparing(FoodCategory::getSortOrder));

        for (FoodCategory foodCategory : foodCategories) {
            if (Objects.equals(UserSession.getCurrentUser().getId(), foodCategory.getUser().getId())) {
                TitledPane titledPane = new TitledPane();
                titledPane.setText(foodCategory.getName());

                ScrollPane scrollPane = new ScrollPane();
                scrollPane.setFitToWidth(true);
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

                VBox vBox = new VBox();
                for (Food food : foodCategory.getFoods()) {
                    FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/compoments/FoodItem.fxml"));
                    Parent item1 = loader1.load();

                    FoodItemController controller1 = loader1.getController();
                    controller1.setData(food.getName(), food.getImgPath());
                    vBox.getChildren().add(item1);
                }

                scrollPane.setContent(vBox);
                titledPane.setContent(scrollPane);
                accordion.getPanes().add(titledPane);
            }
        }
    }

    @Override
    public void createFoodCategory(String name, int sortOrder, boolean active) {
        if (name.isEmpty()) {
            System.out.println("FoodCategory empty");
            return;
        }

        FoodCategory foodCategory = FoodCategory.builder()
                .name(name)
                .sortOrder(sortOrder)
                .active(active)
                .user(UserSession.getCurrentUser())
                .build();

        if (foodCategoryDao.createFoodCategory(foodCategory)) {
            System.out.println("FoodCategory gud");
        } else {
            System.out.println("FoodCategory man");
        }
    }
}
