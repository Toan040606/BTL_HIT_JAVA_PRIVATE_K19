package root.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import root.dao.FoodDao;
import root.model.entity.menu.Food;
import root.util.ConnectDB;

public class FoodDaoImpl implements FoodDao {
    ConnectDB connectDB = new ConnectDB();

    public boolean createFood(Food food) {
        Transaction transaction = null;
        try (Session session = connectDB.open()) {
            transaction = session.beginTransaction();

            session.persist(food);

            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {transaction.rollback();}
            return false;
        }
    }
}
