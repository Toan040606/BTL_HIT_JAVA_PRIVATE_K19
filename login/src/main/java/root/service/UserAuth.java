package root.service;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public interface UserAuth {
    void login(String username, String password, Label lblMsg) throws IOException;
    void register(String firstName, String lastName, String username, String email, String pass, String confirm, Label lblMsg);
    void findEmail(String email, Label lblMsg, BorderPane login, BorderPane register, BorderPane findEmail, BorderPane rsPass);
    void resetPassword(String email, String newPass, String confirm, Label lblMsg);
}
