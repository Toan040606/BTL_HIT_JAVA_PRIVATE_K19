package root.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import root.constant.QuerryMessage;
import root.dao.OptionRequiredGroupDao;
import root.model.entity.menu.OptionRequiredGroup;
import root.util.ConnectDB;

import java.util.List;

public class OptionRequiredGroupDaoImpl implements OptionRequiredGroupDao {
    ConnectDB connectDB = new ConnectDB();

    @Override
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
            e.printStackTrace();
            return false;
        }
    }

    public List<OptionRequiredGroup> findAll() {
        try (Session session = connectDB.open()) {
            List<OptionRequiredGroup> optionRequiredGroups = session
                    .createQuery(QuerryMessage.GET_ALL_ORG, OptionRequiredGroup.class)
                    .getResultList();
            System.out.println(optionRequiredGroups);
            return optionRequiredGroups;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
