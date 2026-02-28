package root.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import root.constant.QuerryMessage;
import root.dao.FoodCategoryDao;
import root.model.entity.core.Area;
import root.model.entity.menu.FoodCategory;
import root.util.ConnectDB;

import java.util.List;

public class FoodCategoryDaoImpl implements FoodCategoryDao {
    ConnectDB connectDB = new ConnectDB();

    @Override
    public List<FoodCategory> findAll() {
        try (Session session = connectDB.open()) {
            List<FoodCategory> allCategories = session
                    .createQuery(QuerryMessage.GET_ALL_FOOD_CATEGORIES, FoodCategory.class)
                    .getResultList();
            System.out.println(allCategories);
            return allCategories;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean createFoodCategory(FoodCategory foodCategory) {
        Transaction transaction = null;
        try (Session session = connectDB.open()) {
            transaction = session.beginTransaction();

            session.persist(foodCategory);

            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            return false;
        }
    }
}
