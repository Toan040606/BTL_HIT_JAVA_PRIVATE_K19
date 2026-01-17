package org.example.dao;

import org.example.constant.SqlMessage;
import org.example.model.User;
import org.example.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserDao {
    // Đăng ký
    public static boolean register(User user){
        String sql = SqlMessage.REGISTER_SQL;
        try{
            Connection conn = DBConnection.getConnection();
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, user.getHoTen());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
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

    // Quên mật khẩu
    public static boolean resetPass(String email,String newPass){
        String sqlCheck = SqlMessage.CHECK_SQL;
        String sqlUpdate = SqlMessage.UPDATE_SQL;


        try{
            Connection conn = DBConnection.getConnection();
            assert conn != null;
            // Check có tồn tại không
            PreparedStatement ps1 = conn.prepareStatement(sqlCheck);
            ps1.setString(1, email);
            if(!ps1.executeQuery().next()){
                return false;
            }

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
