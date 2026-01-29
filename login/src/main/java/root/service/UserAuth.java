package root.service;

import javafx.scene.control.Label;

public interface UserAuth {
    void login(String username, String password, Label lblMsg);
    void register(String firstName, String lastName, String username, String email, String pass, String confirm, Label lblMsg);
    boolean findEmail(String email, Label lblMsg);
    void resetPassword(String email, String newPass, String confirm, Label lblMsg);
    void sendOtpToEmail(String email,Label lblMsg);
    void resendOtpToEmail(String email,Label lblMsg);
    boolean verifyOtp(String email, String otp, Label lblMsg);
}
