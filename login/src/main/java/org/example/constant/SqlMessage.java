package org.example.constant;

public class SqlMessage {
    public static final String REGISTER_SQL  = "INSERT INTO users(ho, ten, username, email, pass) VALUES (?,?,?,?,?)";
    public static final String LOGIN_SQL  = "SELECT userId FROM users WHERE BINARY username = ? AND pass = ?";
    public static final String EXISTS_SQL  = "SELECT 1 FROM users WHERE username = ? || email = ?";
    public static final String CHECK_EMAIL_SQL  = "SELECT 1 FROM users WHERE email = ?";
    public static final String UPDATE_SQL  = "UPDATE users SET pass = ? WHERE email = ?";

}
