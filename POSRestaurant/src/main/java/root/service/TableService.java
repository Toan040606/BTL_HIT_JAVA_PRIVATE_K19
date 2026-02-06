package root.service;

import javafx.scene.control.MenuButton;
import javafx.scene.layout.TilePane;
import root.model.entity.core.Area;

import java.util.List;

public interface TableService {
    void showTable(TilePane tableList);
    void showChooseAllArea(List<Area> areas, MenuButton chooseArea);
}
