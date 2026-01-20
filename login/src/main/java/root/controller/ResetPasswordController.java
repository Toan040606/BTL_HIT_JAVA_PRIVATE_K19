package root.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import root.constant.ErrorMessage;
import root.constant.SuccessMessage;
import root.dao.UserDao;
import root.dao.impl.UserDaoImpl;

import java.io.IOException;

public class ResetPasswordController {
    @FXML private PasswordField txtNewPass;
    @FXML private PasswordField txtConfirm;
    @FXML private Label lblMsg;

    @FXML public void handleResetPassword(){
        UserDao userDao = new UserDaoImpl();
        String email = FindEmailController.resetEmail ;
        String newPass = txtNewPass.getText();
        String confirm = txtConfirm.getText();

        if(newPass.isEmpty() || confirm.isEmpty()){
            lblMsg.setText(ErrorMessage.EMPTY_ERROR);
            lblMsg.setStyle("-fx-text-fill: red;");// đỏ
            return;
        }

        if(!newPass.equals(confirm)){
            lblMsg.setText(ErrorMessage.PASS_ERROR);
            lblMsg.setStyle("-fx-text-fill: red;");// đỏ
            return;
        }

        if(userDao.resetPass(email,newPass)){
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
        Stage stage = (Stage) txtNewPass.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/view/auth/Login.fxml"));
        stage.setScene(new Scene(root));
    }
}
