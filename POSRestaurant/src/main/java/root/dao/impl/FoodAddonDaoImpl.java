package root.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import root.dao.FoodAddonDao;
import root.model.entity.menu.FoodAddon;
import root.util.ConnectDB;

public class FoodAddonDaoImpl implements FoodAddonDao {
    ConnectDB connectDB = new ConnectDB();

    public boolean createFoodAddon(FoodAddon foodAddon) {
        Transaction transaction = null;
        try (Session session = connectDB.open()) {
            transaction = session.beginTransaction();

            session.persist(foodAddon);

            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {transaction.rollback();}
            return false;
        }
    }
}
