package root.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import root.dao.OptionRequiredGroupDao;
import root.model.entity.menu.OptionRequiredGroup;
import root.util.ConnectDB;

public class OptionRequiredGroupDaoImpl implements OptionRequiredGroupDao {
    ConnectDB connectDB = new ConnectDB();

    public boolean createORG(OptionRequiredGroup optionRequiredGroup) {
        Transaction transaction = null;
        try (Session session = connectDB.open()) {
            transaction = session.beginTransaction();

            session.persist(optionRequiredGroup);

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
