package root.service.impl;

import root.dao.FoodCategoryDao;
import root.dao.impl.FoodCategoryDaoImpl;
import root.model.entity.menu.FoodCategory;
import root.service.FoodCategoryService;
import root.util.UserSession;

public class FoodCategoryServiceImpl implements FoodCategoryService {
    FoodCategoryDao foodCategoryDao = new FoodCategoryDaoImpl();

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
