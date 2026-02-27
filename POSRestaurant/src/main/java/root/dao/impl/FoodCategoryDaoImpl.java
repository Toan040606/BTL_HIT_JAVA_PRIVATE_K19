package root.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import root.dao.FoodCategoryDao;
import root.model.entity.menu.FoodCategory;
import root.util.ConnectDB;

public class FoodCategoryDaoImpl implements FoodCategoryDao {
    ConnectDB connectDB = new ConnectDB();

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
