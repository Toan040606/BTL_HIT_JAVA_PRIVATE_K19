package root.service.impl;

import javafx.scene.control.Label;
import root.constant.ErrorMessage;
import root.constant.SuccessMessage;
import root.dao.UserDao;
import root.dao.impl.UserDaoImpl;
import root.model.entity.User;
import root.service.UserAuth;

public class UserAuthImpl implements UserAuth {
    public UserDao userDao = new UserDaoImpl();
    public void login(String username, String password, Label lblMsg) {

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
        }else{
            lblMsg.setText(ErrorMessage.LOGIN_ERROR);
            lblMsg.setStyle("-fx-text-fill: red;");// đỏ
        }
    }

    @Override
    public void register(String firstName, String lastName, String username, String email, String pass, String confirm, Label lblMsg) {
        if(firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || email.isEmpty() || pass.isEmpty() || confirm.isEmpty()){
            lblMsg.setText(ErrorMessage.EMPTY_ERROR);
            lblMsg.setStyle("-fx-text-fill: red;");// đỏ
            return;
        }

        User user = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .username(username)
                .email(email)
                .password(pass)
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
    public void forgotPassword() {

    }
}
