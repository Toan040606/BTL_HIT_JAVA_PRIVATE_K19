package root.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import root.constant.QuerryMessage;
import root.dao.AreaDao;
import root.dao.TableDao;
import root.model.entity.core.TableEntity;
import root.util.ConnectDB;

import java.util.List;

public class TableDaoImpl implements TableDao {
    ConnectDB connectDB = new ConnectDB();
    AreaDao areaDao = new AreaDaoImpl();

    @Override
    public List<TableEntity> findAll() {
        try (Session session = connectDB.open()) {
            List<TableEntity> tables = session
                    .createQuery(QuerryMessage.GET_ALL_TABLE, TableEntity.class)
                    .getResultList();
            System.out.println(tables);
            return tables;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean createTable(TableEntity table) {
        Transaction transaction = null;
        try (Session session = connectDB.open()) {
            transaction = session.beginTransaction();

            session.persist(table);

            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return false;
    }
}
