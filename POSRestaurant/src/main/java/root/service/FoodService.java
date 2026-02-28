package root.service;

import javafx.scene.control.MenuButton;
import root.model.entity.menu.Food;
import root.model.entity.menu.FoodCategory;
import root.model.entity.menu.OptionRequiredGroup;

import java.util.List;

public interface FoodService {
    void createFood(String name, String imgPath, double price, String description, boolean active);
    void showAllFoodCategory(List<FoodCategory> foodCategories, MenuButton choose);
    void showAllFood(List<Food> foods, MenuButton choose);
    void createFoodAddon(String name, double price, boolean active);
    void createORG(String name, boolean active);
    void showAllORG(List<OptionRequiredGroup> optionRequiredGroups, MenuButton choose);
    void createOR(String name, double price);
}