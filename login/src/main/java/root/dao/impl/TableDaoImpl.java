package root.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import root.constant.QuerryMessage;
import root.dao.TableDao;
import root.model.entity.core.TableEntity;
import root.util.ConnectDB;

import java.util.List;

public class TableDaoImpl implements TableDao {
    ConnectDB connectDB = new ConnectDB();

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
        Transaction transaction;
        try (Session session = connectDB.open()) {
            transaction = session.beginTransaction();

            session.persist(table);

            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectDB.closing();
        }
        return false;
    }
}
