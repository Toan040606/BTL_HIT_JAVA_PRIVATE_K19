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

public class RegisterController {
    @FXML private TextField txtHoTen,txtUsername,txtEmail;
    @FXML private PasswordField txtPass;
    @FXML private PasswordField txtConfirm;
    @FXML private Label lblMsg;

    @FXML public void handleRegister(){
        String hoTen = txtHoTen.getText();
        String username = txtUsername.getText();
        String email = txtEmail.getText();
        String pass = txtPass.getText();
        String confirm = txtConfirm.getText();

        if(hoTen.isEmpty() || username.isEmpty() || email.isEmpty() || pass.isEmpty() || confirm.isEmpty()){
            lblMsg.setText(ErrorMessage.EMPTY_ERROR);
            lblMsg.setStyle("-fx-text-fill: red;");// đỏ
            return;
        }

        User user = new User(hoTen,username,email,pass);

        if(UserDao.exists(user)){
            lblMsg.setText(ErrorMessage.EXISTS_ERROR);
            lblMsg.setStyle("-fx-text-fill: red;");// đỏ
            return;
        }

        if(!pass.equals(confirm)){
            lblMsg.setText(ErrorMessage.PASS_ERROR);
            lblMsg.setStyle("-fx-text-fill: red;");// đỏ
            return;
        }

        if(UserDao.register(user)){
            lblMsg.setText(SuccessMessage.REGISTER_SUCCESS);
            lblMsg.setStyle("-fx-text-fill: #00ff99;");// xanh
        }
        else{
            lblMsg.setText(ErrorMessage.REGISTER_ERROR);
            lblMsg.setStyle("-fx-text-fill: red;");// đỏ
        }
    }
    @FXML
    public void goLogin() throws IOException {
        Stage stage = (Stage) txtUsername.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
        stage.setScene(new Scene(root));
    }
}
