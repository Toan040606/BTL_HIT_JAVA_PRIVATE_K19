package root.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import root.dao.AreaDao;
import root.model.entity.core.Area;
import root.util.ConnectDB;

public class AreaDaoImpl implements AreaDao {
    ConnectDB connectDB = new ConnectDB();

    @Override
    public boolean createArea(Area newArea) {
        Transaction transaction = null;
        try (Session session = connectDB.open()) {
            transaction = session.beginTransaction();

            session.persist(newArea);

            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return false;
    }
}
