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

import java.io.IOException;

public class ChangePasswordController {
    @FXML private TextField txtEmail;
    @FXML private PasswordField txtNewPass;
    @FXML private PasswordField txtConfirm;
    @FXML private Label lblMsg;

    @FXML public void handleResetPassword(){
        String email = txtEmail.getText();
        String newPass = txtNewPass.getText();
        String confirm = txtConfirm.getText();

        if(email.isEmpty() || newPass.isEmpty() || confirm.isEmpty()){
            lblMsg.setText(ErrorMessage.EMPTY_ERROR);
            lblMsg.setStyle("-fx-text-fill: red;");// đỏ
            return;
        }

        if(!newPass.equals(confirm)){
            lblMsg.setText(ErrorMessage.PASS_ERROR);
            lblMsg.setStyle("-fx-text-fill: red;");// đỏ
            return;
        }

        if(UserDao.resetPass(email,newPass)){
            lblMsg.setText(SuccessMessage.RESET_PASS_SUCCESS);
            lblMsg.setStyle("-fx-text-fill: #00ff99;");// xanh
        }
        else{
            lblMsg.setText(ErrorMessage.RESET_PASS_ERROR);
            lblMsg.setStyle("-fx-text-fill: red;");// đỏ
        }
    }

    @FXML
    public void goLogin() throws IOException {
        Stage stage = (Stage) txtEmail.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
        stage.setScene(new Scene(root));
    }
}
