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
    exports root.service;       // THÊM DÒNG NÀY
    exports root.service.impl;  // THÊM DÒNG NÀY
    exports root.model.entity;
    exports root.dao;
    exports root.dao.impl;

    opens root.controller to javafx.fxml;
    opens root.model.entity to org.hibernate.orm.core;
}
