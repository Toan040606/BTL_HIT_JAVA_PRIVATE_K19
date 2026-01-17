package org.example.model;

public class User {
    private int userID;
    private String hoTen;
    private String username;
    private String email;
    private String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String hoTen, String username, String email, String password) {
        this.hoTen = hoTen;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }


    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
