package org.example.util;

import org.example.constant.ErrorMessage;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBConnection {
    // Thông tin kết nối
    private static final String url = "jdbc:mysql://localhost:3306/login_javafx?useSSL=false&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "123456";

    // Kết nối
    public static Connection getConnection(){
        try{
            return DriverManager.getConnection(url,user,password);
        }catch (Exception ex){
            System.out.println(ErrorMessage.CONNECTION_ERROR);
            ex.printStackTrace();
            return null;
        }
    }
}