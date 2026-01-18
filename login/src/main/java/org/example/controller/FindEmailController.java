package org.example.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.constant.ErrorMessage;
import org.example.dao.UserDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

import static org.example.dao.UserDao.isEmailExists;

public class FindEmailController {
    public static String resetEmail;

    @FXML
    private Label email_label;

    @FXML
    private Button rsPass_btn;

    @FXML
    private TextField txtEmail;

    @FXML
    private Label lblMsg;

    @FXML
    void goReset(ActionEvent event) throws IOException {
        String email = txtEmail.getText();
        resetEmail = email;

        if (email.isEmpty()) {
            lblMsg.setText(ErrorMessage.EMPTY_ERROR);
            lblMsg.setStyle("-fx-text-fill: red;");// đỏ
            return;
        }

        if (isEmailExists(email)) {
            Stage stage = (Stage) txtEmail.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/view/ResetPassword.fxml"));
            stage.setScene(new Scene(root));
        } else {
            lblMsg.setText(ErrorMessage.INVALID_EMAIL_ERROR);
            lblMsg.setStyle("-fx-text-fill: red;"); // đỏ
        }
    }

    @FXML
    void goLogin(ActionEvent event) throws IOException {
        Stage stage = (Stage) txtEmail.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        stage.setScene(new Scene(root));
    }
}