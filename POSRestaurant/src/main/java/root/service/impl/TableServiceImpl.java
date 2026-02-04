package root.service.impl;

import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import root.dao.TableDao;
import root.dao.impl.TableDaoImpl;
import root.model.entity.core.TableEntity;
import root.service.TableService;

import java.util.ArrayList;
import java.util.List;

public class TableServiceImpl implements TableService {
    TableDao tableDao = new TableDaoImpl();


    @Override
    public void showTable(TilePane tableList) {

    }
}
