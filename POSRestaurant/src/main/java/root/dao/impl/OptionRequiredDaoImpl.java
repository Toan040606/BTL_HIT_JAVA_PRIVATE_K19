package root.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import root.dao.OptionRequiredDao;
import root.model.entity.menu.OptionRequired;
import root.util.ConnectDB;

public class OptionRequiredDaoImpl implements OptionRequiredDao {
    ConnectDB connectDB = new ConnectDB();

    @Override
    public boolean createOR(OptionRequired optionRequired) {
        Transaction transaction = null;
        try (Session session = connectDB.open()) {
            transaction = session.beginTransaction();

            session.persist(optionRequired);

            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
}
