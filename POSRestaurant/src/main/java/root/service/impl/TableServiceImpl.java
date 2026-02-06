package root.service.impl;

import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import root.dao.TableDao;
import root.dao.impl.TableDaoImpl;
import root.model.entity.core.Area;
import root.model.entity.core.TableEntity;
import root.service.TableService;
import root.util.UserSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TableServiceImpl implements TableService {
    TableDao tableDao = new TableDaoImpl();


    @Override
    public void showTable(TilePane tableList) {

    }

    @Override
    public void showChooseAllArea(List<Area> areas, MenuButton chooseArea) {
        areas.forEach((area) -> {
            if (Objects.equals(UserSession.currentUser.getId(), area.getUser().getId())) {
                MenuItem menuItem = new MenuItem(area.getName());
                chooseArea.getItems().add(menuItem);
                menuItem.setOnAction(actionEvent -> {
                    chooseArea.setText(menuItem.getText());
                });
            }
        });
    }


}
