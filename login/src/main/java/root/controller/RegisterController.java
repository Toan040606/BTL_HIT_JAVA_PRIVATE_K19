package root.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import root.constant.ErrorMessage;
import root.constant.SuccessMessage;
import root.dao.impl.UserDaoImpl;
import root.model.entity.User;
import root.service.UserAuth;
import root.service.impl.UserAuthImpl;

import java.io.IOException;

public class RegisterController {
    @FXML private TextField txtHo, txtTen, txtUsername,txtEmail;
    @FXML private PasswordField txtPass;
    @FXML private PasswordField txtConfirm;
    @FXML private Label lblMsg;

    @FXML public void handleRegister(){
        String ho = txtHo.getText();
        String ten = txtTen.getText();
        String username = txtUsername.getText();
        String email = txtEmail.getText();
        String pass = txtPass.getText();
        String confirm = txtConfirm.getText();

        UserAuth userAuth = new UserAuthImpl();

        userAuth.register(ho, ten, username, email, pass, confirm, lblMsg);
    }
    @FXML
    public void goLogin() throws IOException {
        Stage stage = (Stage) txtUsername.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/view/auth/Login.fxml"));
        stage.setScene(new Scene(root));
    }
}
