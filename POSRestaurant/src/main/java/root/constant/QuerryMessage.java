package root.constant;

public class QuerryMessage {
    public static final String USER_LOGIN  = "FROM User WHERE username = :un AND password = :pw";
    public static final String USER_ISEXISTS  = "SELECT count(u) FROM User u WHERE u.username = :un OR u.email = :email";
    public static final String USER_CHECK_EMAIL  = "SELECT 1 FROM User WHERE email = :email";
    public static final String USER_PASSWORD_UPDATE  = "UPDATE User u SET u.password = :pw WHERE u.email = :email";
    public static final String GET_ALL_TABLE = "FROM TableEntity";
    public static final String GET_ALL_AREA = "FROM Area a ORDER BY a.name ASC";
    public static final String EXISTING_ITEM = "FROM OrderItem oi WHERE oi.order.id = :orderId AND oi.food.id = :foodId";
}
