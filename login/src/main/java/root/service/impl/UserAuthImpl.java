package root.service.impl;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import root.constant.ErrorMessage;
import root.constant.SuccessMessage;
import root.dao.UserDao;
import root.dao.impl.UserDaoImpl;
import root.exception.AuthException;
import root.exception.DatabaseException;
import root.model.entity.OtpCode;
import root.model.entity.User;
import root.service.UserAuth;
import root.util.EmailUtil;
import root.util.OtpUtil;
import root.util.PasswordUtil;

public class UserAuthImpl implements UserAuth {
    public UserDao userDao = new UserDaoImpl();
    public void login(String username, String password, Label lblMsg) {
        try{
            if(username.isEmpty() || password.isEmpty()){
                throw new AuthException(ErrorMessage.EMPTY_ERROR);
            }

            User user = User.builder()
                    .username(username)
                    .password(password)
                    .build();

            if(userDao.login(user)){
                lblMsg.setText(SuccessMessage.LOGIN_SUCCESS);
                lblMsg.setStyle("-fx-text-fill: #00ff99;");// xanh

            }else{
                throw new AuthException(ErrorMessage.LOGIN_ERROR);
            }
        } catch (AuthException e){
            lblMsg.setText(e.getMessage());
            lblMsg.setStyle("-fx-text-fill: red;");// đỏ

        } catch (DatabaseException e){
            lblMsg.setText(ErrorMessage.DATABASE_CONNECTION_ERROR);
            lblMsg.setStyle("-fx-text-fill: orange;");// cam
        }
    }

    @Override
    public void register(String firstName, String lastName, String username, String email, String pass, String confirm, Label lblMsg) {
       try{
           if(firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || email.isEmpty() || pass.isEmpty() || confirm.isEmpty()){
               throw new AuthException(ErrorMessage.EMPTY_ERROR);
           }

           if(!pass.equals(confirm)){
               throw new AuthException(ErrorMessage.PASS_ERROR);
           }

           // MÃ Hóa
           String hashedPassword = PasswordUtil.hashPassword(pass);

           User user = User.builder()
                   .firstName(firstName)
                   .lastName(lastName)
                   .username(username)
                   .email(email)
                   .password(hashedPassword)
                   .build();

           if(userDao.exists(user)){
               throw new AuthException(ErrorMessage.EXISTS_ERROR);
           }

           if(userDao.register(user)){
               lblMsg.setText(SuccessMessage.REGISTER_SUCCESS);
               lblMsg.setStyle("-fx-text-fill: #00ff99;");// xanh
           }
           else{
               throw new AuthException(ErrorMessage.REGISTER_ERROR);
           }
       }catch (AuthException e){
           lblMsg.setText(e.getMessage());
           lblMsg.setStyle("-fx-text-fill: red;");// đỏ
       }catch (Exception e){
           lblMsg.setText(ErrorMessage.SYSTEM_ERROR);
           lblMsg.setStyle("-fx-text-fill: red;");// đỏ
           e.printStackTrace();
       }
    }

    @Override
    public void findEmail(String email, Label lblMsg, BorderPane login, BorderPane register, BorderPane findEmail, BorderPane rsPass){
        try{
            if (email.isEmpty()) {
                throw new AuthException(ErrorMessage.EMPTY_ERROR);
            }

            if (userDao.isEmailExists(email)) {
                login.setVisible(false);
                register.setVisible(false);
                findEmail.setVisible(false);
                rsPass.setVisible(true);
                lblMsg.setText("");
            } else {
                throw new AuthException(ErrorMessage.INVALID_EMAIL_ERROR);
            }
        }catch (AuthException e){
            lblMsg.setText(e.getMessage());
            lblMsg.setStyle("-fx-text-fill: red;");
        }catch (DatabaseException e){
            lblMsg.setText(ErrorMessage.DATABASE_CONNECTION_ERROR);
            lblMsg.setStyle("-fx-text-fill: orange;");
        }
    }

    @Override
    public void resetPassword(String email, String newPass, String confirm, Label lblMsg) {
        try{
            if(newPass.isEmpty() || confirm.isEmpty()){
                throw new AuthException(ErrorMessage.EMPTY_ERROR);
            }

            if(!newPass.equals(confirm)){
                throw new AuthException(ErrorMessage.PASS_ERROR);
            }

            // Mã hóa
            String hashedNewPass = PasswordUtil.hashPassword(newPass);

            if(userDao.resetPass(email,hashedNewPass)){
                lblMsg.setText(SuccessMessage.RESET_PASS_SUCCESS);
                lblMsg.setStyle("-fx-text-fill: #00ff99;");// xanh
            }
            else{
                throw new AuthException(ErrorMessage.RESET_PASS_ERROR);
            }
        }catch (AuthException e){
            lblMsg.setText(e.getMessage());
            lblMsg.setStyle("-fx-text-fill: red;");
        }
        catch (DatabaseException e){
            lblMsg.setText(ErrorMessage.DATABASE_CONNECTION_ERROR);
            lblMsg.setStyle("-fx-text-fill: orange;");
        }
    }

    public void sendOtpToEmail(String email, Label lblMsg) {
        try{
            if(email.isEmpty()){
                throw new AuthException(ErrorMessage.EMPTY_ERROR);
            }

            if(!userDao.isEmailExists(email)){
                throw new AuthException(ErrorMessage.INVALID_EMAIL_ERROR);
            }

            String otp = OtpUtil.otpCode();

            OtpCode otpCode = OtpCode.builder()
                    .email(email)
                    .otp(otp)
                    .expiredAt(OtpUtil.expiredAfterMinutes(5))
                    .build();
            EmailUtil.sendOtp(email,otp);
            userDao.saveOtp(otpCode);

            lblMsg.setText(SuccessMessage.SEND_OTP_SUCCESS);
            lblMsg.setStyle("-fx-text-fill: #00ff99;");
        }catch (AuthException e){
            lblMsg.setText(e.getMessage());
            lblMsg.setStyle("-fx-text-fill: red;");
        }catch (Exception e){
            lblMsg.setText(ErrorMessage.SYSTEM_ERROR);
            lblMsg.setStyle("-fx-text-fill: orange;");
            e.printStackTrace();
        }
    }

}
