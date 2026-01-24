module root.login {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires static lombok;
    requires jakarta.persistence;
    requires mysql.connector.j;
    requires org.hibernate.orm.core;
    requires annotations;
    requires java.naming;

    exports root;
    exports root.controller;
    exports root.service;
    exports root.service.impl;
    exports root.dao;
    exports root.dao.impl;
    exports root.model.entity.order.detail;
    exports root.model.entity.core;
    exports root.model.entity.menu;

    opens root.controller to javafx.fxml;
    opens root.model.entity.order.detail to org.hibernate.orm.core;
    opens root.model.entity.core to org.hibernate.orm.core;
    opens root.model.entity.menu to org.hibernate.orm.core;
}
