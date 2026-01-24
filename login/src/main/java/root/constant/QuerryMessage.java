package root.constant;

public class QuerryMessage {
    public static final String USER_LOGIN  = "FROM User WHERE username = :un";
    public static final String USER_ISEXISTS  = "SELECT count(u) FROM User u WHERE u.username = :un OR u.email = :email";
    public static final String USER_CHECK_EMAIL  = "SELECT count(u) FROM User u  WHERE u.email = :email";
    public static final String USER_PASSWORD_UPDATE  = "UPDATE User SET password = :pw WHERE email = :email";
    public static final String OTP_VALID  = "FROM OtpCode o WHERE o.email = :email AND o.otp = :otp AND o.expiredAt > :now";
    public static final String DELETE_OTP  = "DELETE FROM OtpCode o WHERE o.email = :email";

}
