package root.dao;

import root.model.entity.core.Area;

import java.util.List;

public interface AreaDao {
    boolean createArea(Area newArea);
    List<Area> findAll();
}
