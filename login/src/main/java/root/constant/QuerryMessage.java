package root.constant;

public class QuerryMessage {
    public static final String USER_LOGIN  = "FROM User WHERE BINARY username = :un AND password = :pw";
    public static final String USER_ISEXISTS  = "SELECT count(u) FROM User u WHERE u.username = :un OR u.email = :email";
    public static final String USER_CHECK_EMAIL  = "SELECT 1 FROM User WHERE q email = :email";
    public static final String USER_PASSWORD_UPDATE  = "UPDATE User SET password = :pw WHERE email = :email";

}
