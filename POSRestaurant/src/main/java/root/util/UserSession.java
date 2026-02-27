package root.util;

import lombok.Getter;
import lombok.Setter;
import root.model.entity.core.User;

public class UserSession {
    @Getter
    @Setter
    public static User currentUser;

    public static void logout() {
        currentUser = null;
    }
}
