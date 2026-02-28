package root.service.impl;

import javafx.scene.control.*;
import root.dao.FoodAddonDao;
import root.dao.FoodDao;
import root.dao.OptionRequiredDao;
import root.dao.OptionRequiredGroupDao;
import root.dao.impl.FoodAddonDaoImpl;
import root.dao.impl.FoodDaoImpl;
import root.dao.impl.OptionRequiredDaoImpl;
import root.dao.impl.OptionRequiredGroupDaoImpl;
import root.model.entity.menu.*;
import root.service.FoodService;
import root.util.UserSession;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Locale.filter;

public class FoodServiceImpl implements FoodService {
    FoodCategory foodCategoryNow;
    List<Food> choosingFoods = new ArrayList<>();
    OptionRequiredGroup  optionRequiredGroupNow;

    FoodDao foodDao = new FoodDaoImpl();
    FoodAddonDao foodAddonDao = new FoodAddonDaoImpl();
    OptionRequiredGroupDao optionRequiredGroupDao = new OptionRequiredGroupDaoImpl();
    OptionRequiredDao optionRequiredDao = new OptionRequiredDaoImpl();

    @Override
    public void showAllFoodCategory(List<FoodCategory> foodCategories, MenuButton choose) {
        foodCategories.forEach(foodCategory -> {
            if (Objects.equals(UserSession.currentUser.getId(), foodCategory.getUser().getId())) {
                MenuItem menuItem = new MenuItem(foodCategory.getName());
                choose.getItems().add(menuItem);
                menuItem.setOnAction(e -> {
                    choose.setText(menuItem.getText());
                    foodCategoryNow = foodCategory;
                });
            }
        });
    }

    @Override
    public void showAllFood(List<Food> foods, MenuButton choose) {
        choosingFoods.clear();
        foods.forEach(food -> {
            if (Objects.equals(UserSession.currentUser.getId(), food.getCategory().getUser().getId())) {
                CheckMenuItem menuItem = new CheckMenuItem(food.getName());
                choose.getItems().add(menuItem);
                menuItem.setOnAction(e -> {
                    if (menuItem.isSelected() && !choosingFoods.contains(food)) {
                        choosingFoods.add(food);
                        System.out.println(choosingFoods);
                    } else {
                        choosingFoods.remove(food);
                        System.out.println(choosingFoods);
                    }
                });
            }
        });
    }

    @Override
    public void showAllORG(List<OptionRequiredGroup> optionRequiredGroups, MenuButton choose) {
        optionRequiredGroups.forEach(optionRequiredGroup -> {
            boolean isSameUser = optionRequiredGroup.getFoods().stream()
                    .filter(Objects::nonNull)
                    .anyMatch(food -> Objects.equals(UserSession.currentUser.getId(), food.getCategory().getUser().getId()));

            if (isSameUser) {
                MenuItem menuItem = new MenuItem(optionRequiredGroup.getName());
                choose.getItems().add(menuItem);
                menuItem.setOnAction(e -> {
                    choose.setText(menuItem.getText());
                    optionRequiredGroupNow =  optionRequiredGroup;
                });
            }
        });
    }

    @Override
    public void createFood(String name
            , String imgPath
            , double price
            , String description
            , boolean active) {
        if (name.isEmpty() || price < 0) {
            System.out.println("empty");
            return;
        }

        if (Objects.equals(imgPath, "null") || imgPath.isEmpty()) {
            imgPath = "D:\\Code\\BTL_Java_Private_K19\\POSRestaurant\\src\\main\\resources\\assets\\defaultfood.jpg";
        }

        Food food = Food.builder()
                .name(name)
                .imgPath(imgPath)
                .price(BigDecimal.valueOf(price))
                .description(description)
                .active(active)
                .category(foodCategoryNow)
                .build();

        if (foodDao.createFood(food)) {


            System.out.println("Food gud");
        } else {
            System.out.println("Food man");
        }
    }

    @Override
    public void createFoodAddon(String name, double price, boolean active) {
        if  (name.isEmpty() || price < 0 || choosingFoods.isEmpty()) {
            return;
        }

        FoodAddon foodAddon = FoodAddon.builder()
                .name(name)
                .price(price)
                .active(active)
                .foods(new ArrayList<>(choosingFoods))
                .build();

        if (foodAddonDao.createFoodAddon(foodAddon)) {
            System.out.println("FoodAddon gud");
        } else {
            System.out.println("FoodAddon man");
        }
    }

    @Override
    public void createORG(String name, boolean active) {
        if (name.isEmpty() || choosingFoods.isEmpty()) {
            return;
        }

        OptionRequiredGroup optionRequiredGroup = OptionRequiredGroup.builder()
                .name(name)
                .active(active)
                .foods(new ArrayList<>(choosingFoods))
                .build();

        if (optionRequiredGroupDao.createORG(optionRequiredGroup)) {
            System.out.println("ORG gud");
        } else {
            System.out.println("ORG man");
        }
    }

    @Override
    public void createOR(String name, double price) {
        if (name.isEmpty() || price < 0 || optionRequiredGroupNow == null) {
            return;
        }

        OptionRequired optionRequired = OptionRequired.builder()
                .name(name)
                .price(price)
                .optionRequiredGroup(optionRequiredGroupNow)
                .build();

        if (optionRequiredDao.createOR(optionRequired)) {
            System.out.println("OR gud");
        } else {
            System.out.println("OR man");
        }
    }
}
