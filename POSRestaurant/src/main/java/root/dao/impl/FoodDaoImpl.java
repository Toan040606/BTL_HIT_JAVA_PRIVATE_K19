package root.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import root.constant.QuerryMessage;
import root.dao.FoodDao;
import root.model.entity.menu.Food;
import root.model.entity.menu.FoodCategory;
import root.util.ConnectDB;

import java.util.List;

public class FoodDaoImpl implements FoodDao {
    ConnectDB connectDB = new ConnectDB();



    @Override
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

    @Override
    public List<Food> findAll() {
        try (Session session = connectDB.open()) {
            List<Food> allFoods = session
                    .createQuery(QuerryMessage.GET_ALL_FOODS, Food.class)
                    .getResultList();
            System.out.println(allFoods);
            return allFoods;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
