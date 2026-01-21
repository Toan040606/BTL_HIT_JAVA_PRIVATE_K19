package root.service.impl;

import javafx.scene.control.Label;
import root.constant.ErrorMessage;
import root.constant.SuccessMessage;
import root.dao.UserDao;
import root.dao.impl.UserDaoImpl;
import root.exception.AuthException;
import root.exception.DatabaseException;
import root.model.entity.User;
import root.service.UserAuth;
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
    public void forgotPassword() {

    }
}
