package root.dao;

import root.model.entity.core.TableEntity;

import java.util.List;

public interface TableDao {
    List<TableEntity> findAll();
}
