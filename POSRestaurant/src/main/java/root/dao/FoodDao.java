package root.dao;

import root.model.entity.menu.Food;

import java.util.List;

public interface FoodDao {
    boolean createFood(Food food);
    List<Food> findAll();
}
