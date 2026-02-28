package root.dao;

import root.model.entity.menu.OptionRequiredGroup;

import java.util.List;

public interface OptionRequiredGroupDao {
    boolean createORG(OptionRequiredGroup optionRequiredGroup);
    List<OptionRequiredGroup> findAll();
}
