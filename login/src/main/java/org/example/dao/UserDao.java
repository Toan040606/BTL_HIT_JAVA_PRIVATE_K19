package org.example.dao;

import org.example.constant.SqlMessage;
import org.example.model.User;
import org.example.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {
    // Đăng ký
    public static boolean register(User user){
        String sql = SqlMessage.REGISTER_SQL;
        try{
            Connection conn = DBConnection.getConnection();
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, user.getHo());
            ps.setString(2, user.getTen());
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPassword());
            return ps.executeUpdate() > 0;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    // Đăng nhập
    public static boolean login(User user){
        String sql = SqlMessage.LOGIN_SQL;
        try{
            Connection conn = DBConnection.getConnection();
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());

            return ps.executeQuery().next();
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    // Kiểm tra tồn tại
    public static boolean exists(User user){
        String sql = SqlMessage.EXISTS_SQL;
        try {
            Connection conn = DBConnection.getConnection();
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            return ps.executeQuery().next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Tạo lại mật khẩu
    public static boolean isEmailExists(String email) {
        String sqlCheck = SqlMessage.CHECK_EMAIL_SQL;

        try {
            Connection conn = DBConnection.getConnection();
            assert conn != null;

            PreparedStatement ps = conn.prepareStatement(sqlCheck);
            ps.setString(1, email);
            return ps.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean resetPass(String email, String newPass){
        String sqlUpdate = SqlMessage.UPDATE_SQL;


        try{
            Connection conn = DBConnection.getConnection();
            assert conn != null;

            // Update
            PreparedStatement ps2 = conn.prepareStatement(sqlUpdate);
            ps2.setString(1,newPass);
            ps2.setString(2,email);
            return ps2.executeUpdate() > 0;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }

    }
}
