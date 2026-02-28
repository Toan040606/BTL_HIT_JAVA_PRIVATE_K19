package root.service.impl;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import root.constant.ErrorMessage;
import root.constant.SuccessMessage;
import root.dao.UserDao;
import root.dao.impl.UserDaoImpl;
import root.model.entity.OtpCode;
import root.model.entity.core.User;
import root.service.UserAuth;
import root.util.EmailUtil;
import root.util.OtpUtil;
import root.util.PasswordUtil;
import root.util.UserSession;

import java.io.IOException;
import java.util.Objects;


public class UserAuthImpl implements UserAuth {
    public UserDao userDao = new UserDaoImpl();

    public void login(String username, String password, Label lblMsg) throws IOException {

        if(username.isEmpty() || password.isEmpty()){
            lblMsg.setText(ErrorMessage.EMPTY_ERROR);
            lblMsg.setStyle("-fx-text-fill: red;");// đỏ
            return;
        }

        User user = User.builder()
                .username(username)
                .password(password)
                .build();

        if(userDao.login(user)){
            lblMsg.setText(SuccessMessage.LOGIN_SUCCESS);
            lblMsg.setStyle("-fx-text-fill: #00ff99;");// xanh
            Stage stage = (Stage) lblMsg.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/view/Application.fxml"));
            stage.setScene(new Scene(root));
        }else{
            lblMsg.setText(ErrorMessage.LOGIN_ERROR);
            lblMsg.setStyle("-fx-text-fill: red;");// đỏ
        }
    }

    @Override
    public void register(String firstName, String lastName, String username, String email, String pass, String confirm, Label lblMsg) {
        if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || email.isEmpty() || pass.isEmpty() || confirm.isEmpty()){
            lblMsg.setText(ErrorMessage.EMPTY_ERROR);
            lblMsg.setStyle("-fx-text-fill: red;");// đỏ
            return;
        }

        String hashedPassword = PasswordUtil.hashPassword(pass);

        User user = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .username(username)
                .email(email)
                .password(hashedPassword)
                .build();

        if(userDao.exists(user)){
            lblMsg.setText(ErrorMessage.EXISTS_ERROR);
            lblMsg.setStyle("-fx-text-fill: red;");// đỏ
            return;
        }

        if(!pass.equals(confirm)){
            lblMsg.setText(ErrorMessage.PASS_ERROR);
            lblMsg.setStyle("-fx-text-fill: red;");// đỏ
            return;
        }

        if(userDao.register(user)){
            lblMsg.setText(SuccessMessage.REGISTER_SUCCESS);
            lblMsg.setStyle("-fx-text-fill: #00ff99;");// xanh
        }
        else{
            lblMsg.setText(ErrorMessage.REGISTER_ERROR);
            lblMsg.setStyle("-fx-text-fill: red;");// đỏ
        }
    }

    @Override
    public void findEmail(String email, Label lblMsg, Button button){
        if (email.isEmpty()) {
            lblMsg.setText(ErrorMessage.EMPTY_ERROR);
            lblMsg.setStyle("-fx-text-fill: red;");// đỏ
            return;
        }

        if (userDao.isEmailExists(email)) {
            sendOtpToEmail(email, lblMsg);
            button.setText("Gửi lại");
        } else {
            lblMsg.setText(ErrorMessage.INVALID_EMAIL_ERROR);
            lblMsg.setStyle("-fx-text-fill: red;"); // đỏ
        }
    }

    @Override
    public void resetPassword(String email, String newPass, String confirm, Label lblMsg) {
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

        String hashedNewPass = PasswordUtil.hashPassword(newPass);

        if(userDao.resetPass(email, hashedNewPass)){
            lblMsg.setText(SuccessMessage.RESET_PASS_SUCCESS);
            lblMsg.setStyle("-fx-text-fill: #00ff99;");// xanh
        }
        else{
            lblMsg.setText(ErrorMessage.RESET_PASS_ERROR);
            lblMsg.setStyle("-fx-text-fill: red;");// đỏ
        }
    }

    public void logout(ImageView logoutBtn) throws IOException {
        UserSession.logout();
        Stage stage = (Stage) logoutBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/view/Auth.fxml"));
        stage.setScene(new Scene(root));
    }

    @Override
    public boolean verifyOtp(String email, String otp, Label lblMsg) {
        try {
            if (otp == null || otp.isEmpty()) {
                System.out.println(ErrorMessage.EMPTY_ERROR);
            }

            OtpCode otpCode = userDao.findValidOtp(email, otp);

            if (otpCode == null) {
                System.out.println(ErrorMessage.VALID_OTP_ERROR);
            }

            assert otpCode != null;
            if (!(Objects.equals(otp, otpCode.getOtp()))) {
                return false;
            }

            lblMsg.setText(SuccessMessage.OTP_VERIFY_SUCCESS);
            lblMsg.setStyle("-fx-text-fill: #00ff99;");

            return true;
        } catch (Exception e) {
            lblMsg.setText(ErrorMessage.SYSTEM_ERROR);
            lblMsg.setStyle("-fx-text-fill: orange;");
            e.printStackTrace();
            return false;
        }
    }

    private void processOtp(String email,Label lblMsg,String successMsg) {
        if(email.isEmpty()){
            System.out.println(ErrorMessage.EMPTY_ERROR);
        }

        if(!userDao.isEmailExists(email)){
            System.out.println(ErrorMessage.INVALID_EMAIL_ERROR);
        }

        String otp = OtpUtil.otpCode();

        OtpCode otpCode = OtpCode.builder()
                .email(email)
                .otp(otp)
                .expiredAt(OtpUtil.expiredAfterMinutes(5))
                .build();
        EmailUtil.sendOtp(email,otp);
        userDao.saveOtp(otpCode);

        lblMsg.setText(successMsg);
        lblMsg.setStyle("-fx-text-fill: #00ff99;");
    }

    @Override
    public void sendOtpToEmail(String email, Label lblMsg) {
        try{
            processOtp(email,lblMsg,SuccessMessage.SEND_OTP_SUCCESS);
        }catch (Exception e){
            lblMsg.setText(ErrorMessage.SYSTEM_ERROR);
            lblMsg.setStyle("-fx-text-fill: orange;");
            e.printStackTrace();
        }
    }

    @Override
    public void resendOtpToEmail(String email,Label lblMsg) {
        try{
            processOtp(email,lblMsg,SuccessMessage.RESEND_OTP_SUCCESS);
        }catch (Exception e){
            lblMsg.setText(ErrorMessage.SYSTEM_ERROR);
            lblMsg.setStyle("-fx-text-fill: orange;");
            e.printStackTrace();
        }
    }
}
