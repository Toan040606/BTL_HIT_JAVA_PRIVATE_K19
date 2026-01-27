package root.dao;

import root.model.entity.User;

public interface UserDao {
    boolean login(User user);
    boolean register(User user);
    boolean exists(User user);
    boolean isEmailExists(String email);
    boolean resetPass(String email, String newPass);
}
