package root.service;

import javafx.scene.control.Accordion;

import java.io.IOException;

public interface FoodCategoryService {
    void showCategoryOnFoodManager(Accordion accordion) throws IOException;
    void createFoodCategory(String name, int sortOrder, boolean active);
}
