package org.example.util;

import org.example.constant.ErrorMessage;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBConnection {
    // Thông tin kết nối
    private static final String url = "jdbc:mysql://localhost:3306/user_db";
    private static final String user = "root";
    private static final String password = "1234";

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