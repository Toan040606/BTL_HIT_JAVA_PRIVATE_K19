package root.dao;

import root.model.entity.menu.FoodCategory;

import java.util.List;

public interface FoodCategoryDao {
    List<FoodCategory> findAll();
    boolean createFoodCategory(FoodCategory foodCategory);
}
