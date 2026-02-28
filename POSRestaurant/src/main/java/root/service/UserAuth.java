package root.service;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public interface UserAuth {
    void login(String username, String password, Label lblMsg) throws IOException;
    void register(String firstName, String lastName, String username, String email, String pass, String confirm, Label lblMsg);
    void findEmail(String email, Label lblMsg, Button button);
    void resetPassword(String email, String newPass, String confirm, Label lblMsg);
    void logout(ImageView logoutBtn) throws IOException;
    void resendOtpToEmail(String email,Label lblMsg);
    void sendOtpToEmail(String email, Label lblMsg);
    boolean verifyOtp(String email, String otp, Label lblMsg);

}
