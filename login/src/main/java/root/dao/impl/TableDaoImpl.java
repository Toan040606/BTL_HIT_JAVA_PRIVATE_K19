package root.dao.impl;

import root.dao.TableDao;
import root.model.entity.core.TableEntity;

import java.util.ArrayList;
import java.util.List;

public class TableDaoImpl implements TableDao {

    @Override
    public List<TableEntity> findAll() {
        List<TableEntity> tables = new ArrayList<>();
        return List.of();
    }

    @Override
    public void insert() {

    }
}
