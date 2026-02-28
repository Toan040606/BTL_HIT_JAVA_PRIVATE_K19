package root.service;

import javafx.scene.control.MenuButton;
import javafx.scene.control.TabPane;
import javafx.scene.layout.TilePane;
import root.model.entity.core.Area;

import java.io.IOException;
import java.util.List;

public interface TableService {
    void showTableOnTablePage(TilePane tableList, boolean isAll) throws IOException;
    void showTableOnConfigurationPage(TabPane tabPaneArea) throws IOException;
    void showChooseAllArea(List<Area> areas, MenuButton chooseArea);
    void createTable(String tableName, int seats);
}
