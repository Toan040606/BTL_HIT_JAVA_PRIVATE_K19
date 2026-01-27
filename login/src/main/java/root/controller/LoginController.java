package root.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import root.service.UserAuth;
import root.service.impl.UserAuthImpl;

import java.io.IOException;

public class LoginController {
    @FXML private TextField txtUser;
    @FXML private PasswordField txtPass;
    @FXML private Label lblMsg;

    @FXML
    public void initialize() {

    }

    @FXML
    public void handleLogin(){
        UserAuth userAuth = new UserAuthImpl();
        
        String username = txtUser.getText();
        String password = txtPass.getText();
        userAuth.login(username, password, lblMsg);
    }

    @FXML
    public void goRegister() throws IOException {
        Stage stage = (Stage) txtUser.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/view/auth/Register.fxml"));
        stage.setScene(new Scene(root));
    }

    @FXML
    public void goForgotPassword() throws IOException {
        Stage stage = (Stage) txtUser.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/view/auth/FindEmail.fxml"));
        stage.setScene(new Scene(root));
    }
}
