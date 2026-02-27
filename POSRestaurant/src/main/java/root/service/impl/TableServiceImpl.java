package root.service.impl;

import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import root.constant.ErrorMessage;
import root.dao.TableDao;
import root.dao.impl.TableDaoImpl;
import root.model.entity.core.Area;
import root.model.entity.core.TableEntity;
import root.model.enums.TableStatus;
import root.service.TableService;
import root.util.UserSession;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class TableServiceImpl implements TableService {
    public static Area tableArea;
    TableDao tableDao = new TableDaoImpl();

    @Override
    public void showTable(TilePane tableList) {
        List<TableEntity> tables = tableDao.findAll();

        tables.sort(Comparator.comparing(tableEntity -> tableEntity.getArea().getName()));

        for (TableEntity tableEntity : tables) {
            System.out.println(tableEntity);
        }
    }

    @Override
    public void showChooseAllArea(List<Area> areas, MenuButton chooseArea) {
        areas.forEach((area) -> {
            if (Objects.equals(UserSession.currentUser.getId(), area.getUser().getId())) {
                MenuItem menuItem = new MenuItem(area.getName());
                chooseArea.getItems().add(menuItem);
                menuItem.setOnAction(actionEvent -> {
                    chooseArea.setText(menuItem.getText());
                    System.out.println(area);
                    tableArea = area;
                });
            }
        });
    }

    @Override
    public void createTable(String tableName, int seats) {
        TableDao tableDao = new TableDaoImpl();

        if (tableName.isEmpty() || seats <= 0) {
            System.out.println(ErrorMessage.EMPTY_ERROR);
            return;
        }

        TableEntity tableEntity = TableEntity.builder()
                .name(tableName)
                .seatCount(seats)
                .status(TableStatus.AVAILABLE)
                .area(tableArea)
                .qrLink("?table=")
                .build();

        if (tableDao.createTable(tableEntity)) {
            tableEntity.setQrLink("?table=" + tableEntity.getId());

            tableDao.update(tableEntity);

            System.out.println("table gud");
        } else {
            System.out.println("table man");
        }
    }
}
