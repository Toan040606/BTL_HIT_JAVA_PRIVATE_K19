package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.constant.ErrorMessage;
import org.example.constant.SuccessMessage;
import org.example.dao.UserDao;
import org.example.model.User;

import java.io.IOException;

public class LoginController {
    @FXML private TextField txtUser;
    @FXML private PasswordField txtPass;
    @FXML private Label lblMsg;

    @FXML public void handleLogin(){
        String username = txtUser.getText();
        String password = txtPass.getText();

        if(username.isEmpty() || password.isEmpty()){
            lblMsg.setText(ErrorMessage.EMPTY_ERROR);
            lblMsg.setStyle("-fx-text-fill: red;");// đỏ
            return;
        }
        User user = new User(username,password);

        if(UserDao.login(user)){
            lblMsg.setText(SuccessMessage.LOGIN_SUCCESS);
            lblMsg.setStyle("-fx-text-fill: #00ff99;");// xanh
        }else{
            lblMsg.setText(ErrorMessage.LOGIN_ERROR);
            lblMsg.setStyle("-fx-text-fill: red;");// đỏ
        }
    }

    @FXML
    public void goRegister() throws IOException {
        Stage stage = (Stage) txtUser.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/register.fxml"));
        stage.setScene(new Scene(root));
    }

    @FXML
    public void goForgotPassword() throws IOException {
        Stage stage = (Stage) txtUser.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/password.fxml"));
        stage.setScene(new Scene(root));
    }
}
