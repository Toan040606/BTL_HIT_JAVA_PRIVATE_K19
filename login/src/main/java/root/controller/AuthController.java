package root.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import root.service.UserAuth;
import root.service.impl.UserAuthImpl;

public class AuthController {
    private final UserAuth userAuth = new UserAuthImpl();
    public String email;

    @FXML
    private Label emailRegisterLabel;

    @FXML
    private BorderPane findEmailForm;

    @FXML
    private Label findEmailLabel;

    @FXML
    private Button findEmail_btn;

    @FXML
    private Label firstname_label;

    @FXML
    private Button goLoginFindEmail;

    @FXML
    private Button goLoginNewPass;

    @FXML
    private Button goLoginRegister;

    @FXML
    private Button goRegister;

    @FXML
    private Label lastname_label;

    @FXML
    private Label lblMsgFindEmail;

    @FXML
    private Label lblMsgLogin;

    @FXML
    private Label lblMsgNewPass;

    @FXML
    private Label lblMsgRegister;

    @FXML
    private Button loginBtn;

    @FXML
    private BorderPane loginForm;

    @FXML
    private Label passConfirmRegisterLabel;

    @FXML
    private Label passLoginLabel;

    @FXML
    private Label passwordRegisterLabel;

    @FXML
    private Button registerBtn;

    @FXML
    private BorderPane registerForm;

    @FXML
    private CheckBox rememberMe;

    @FXML
    private Button resetPass_btn;

    @FXML
    private BorderPane rsPasswordForm;

    @FXML
    private PasswordField txtConfirmNewPass;

    @FXML
    private PasswordField txtConfirmRegister;

    @FXML
    private TextField txtEmailRegister;

    @FXML
    private TextField txtFindEmail;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private PasswordField txtNewPass;

    @FXML
    private PasswordField txtPassLogin;

    @FXML
    private PasswordField txtPassRegister;

    @FXML
    private TextField txtUsernameLogin;

    @FXML
    private TextField txtUsernameRegister;

    @FXML
    private Label usernameLoginLabel;

    @FXML
    private Label usernameRegisterLabel;

    @FXML
    void initialize() {
        loginForm.setVisible(true);
        registerForm.setVisible(false);
        findEmailForm.setVisible(false);
        rsPasswordForm.setVisible(false);
    }

    @FXML
    void goForgotPassword(ActionEvent event) {
        loginForm.setVisible(false);
        registerForm.setVisible(false);
        findEmailForm.setVisible(true);
        rsPasswordForm.setVisible(false);
    }

    @FXML
    void goLogin(ActionEvent event) {
        ((Button)event.getTarget()).getParent().getParent().setVisible(true);
        registerForm.setVisible(false);
        findEmailForm.setVisible(false);
        rsPasswordForm.setVisible(false);

        loginForm.setVisible(true);
    }

    @FXML
    void goRegister(ActionEvent event) {
        loginForm.setVisible(false);
        registerForm.setVisible(true);
    }

    @FXML
    void handleFindEmailToGoResetPass(ActionEvent event) {
        email = txtFindEmail.getText();
        userAuth.findEmail(email, lblMsgFindEmail, loginForm, registerForm, findEmailForm, rsPasswordForm);
    }

    @FXML
    void handleLogin(ActionEvent event) {
        String username = txtUsernameLogin.getText();
        String password = txtPassLogin.getText();
        userAuth.login(username, password, lblMsgLogin);
    }

    @FXML
    void handleRegister(ActionEvent event) {
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String username = txtUsernameRegister.getText();
        String email = txtEmailRegister.getText();
        String password = txtPassRegister.getText();
        String confirm = txtConfirmRegister.getText();

        userAuth.register(firstName, lastName, username, email, password, confirm, lblMsgRegister);
    }

    @FXML
    void handleResetPassword(ActionEvent event) {
        String newPass = txtNewPass.getText();
        String confirm = txtConfirmNewPass.getText();

        userAuth.resetPassword(email, newPass, confirm, lblMsgNewPass);
    }

}