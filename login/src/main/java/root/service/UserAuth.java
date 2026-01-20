package root.service;

import javafx.scene.control.Label;

public interface UserAuth {
    void login(String username, String password, Label lblMsg);
    void register(String firstName, String lastName, String username, String email, String pass, String confirm, Label lblMsg);
    void forgotPassword();
}
