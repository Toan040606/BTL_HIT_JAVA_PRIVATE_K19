package root.constant;

public class QuerryMessage {
    public static final String USER_LOGIN  = "FROM User WHERE username = :un";
    public static final String USER_ISEXISTS  = "SELECT count(u) FROM User u WHERE u.username = :un OR u.email = :email";
    public static final String USER_CHECK_EMAIL  = "SELECT 1 FROM User WHERE email = :email";
    public static final String USER_PASSWORD_UPDATE  = "UPDATE User u SET u.password = :pw WHERE u.email = :email";
    public static final String GET_ALL_TABLE = "FROM TableEntity";
    public static final String GET_ALL_AREA = "FROM Area a ORDER BY a.name ASC";
    public static final String GET_ALL_FOOD_CATEGORIES = "SELECT DISTINCT a FROM FoodCategory a LEFT JOIN FETCH a.foods ORDER BY a.sortOrder ASC";
    public static final String GET_ALL_FOODS = "SELECT f FROM Food f " +
            "JOIN FETCH f.category c " +
            "JOIN FETCH c.user u " +
            "ORDER BY f.name ASC";
    public static final String GET_ALL_ORG = "SELECT DISTINCT o FROM OptionRequiredGroup o LEFT JOIN FETCH o.foods f " +
            "LEFT JOIN FETCH f.category c " +
            "LEFT JOIN FETCH c.user u ";
}
