package root.service.impl;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import root.constant.ErrorMessage;
import root.controller.components.TableCardController;
import root.controller.components.TableTabComponentController;
import root.controller.components.TableTabController;
import root.dao.AreaDao;
import root.dao.TableDao;
import root.dao.impl.AreaDaoImpl;
import root.dao.impl.TableDaoImpl;
import root.model.entity.core.Area;
import root.model.entity.core.TableEntity;
import root.model.enums.TableStatus;
import root.service.TableService;
import root.util.UserSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class TableServiceImpl implements TableService {
    public static Area tableArea;
    TableDao tableDao = new TableDaoImpl();
    AreaDao areaDao = new AreaDaoImpl();

    @Override
    public void showTableOnTablePage(TilePane tableList, boolean isAll) throws IOException {
        List<TableEntity> tables = tableDao.findAll();

        tables.sort(Comparator.comparing(tableEntity -> tableEntity.getArea().getName()));

        if (isAll) {
            for (TableEntity tableEntity : tables) {
                goToTablePage(tableList, tableEntity);
            }
        }
        else {
            for (TableEntity tableEntity : tables) {
                if (tableEntity.getStatus() == TableStatus.OCCUPIED) {
                    goToTablePage(tableList, tableEntity);
                }
            }
        }
    }

    private void goToTablePage(TilePane tableList, TableEntity tableEntity) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/compoments/TableCard.fxml"));
        Parent item = loader.load();

        TableCardController controller = loader.getController();
        controller.setData(tableEntity.getSeatCount(), tableEntity.getArea().getName(),tableEntity.getName(), String.valueOf(tableEntity.getStatus()));

        tableList.getChildren().add(item);
    }

    @Override
    public void showTableOnConfigurationPage(TabPane tabPaneArea) throws IOException {
        List<Area> areas = areaDao.findAll();

        areas.sort(Comparator.comparing(Area::getName));

        for (Area area : areas) {
            if (Objects.equals(UserSession.currentUser.getId(), area.getUser().getId())) {
                FlowPane tableList = new FlowPane();
                tableList.setPadding(new Insets(10, 0, 10, 0));
                tableList.setAlignment(Pos.TOP_CENTER);

                for (TableEntity table : area.getTableList()) {
                    FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/compoments/TableTabCompoments.fxml"));
                    Parent item1 = loader1.load();

                    TableTabComponentController controller1 = loader1.getController();
                    controller1.setData(table.getName());
                    tableList.getChildren().add(item1);
                }

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/compoments/TableTab.fxml"));
                Tab item = loader.load();

                TableTabController controller = loader.getController();
                controller.setData(area.getName(), tableList);
                tabPaneArea.getTabs().add(item);
            }
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
