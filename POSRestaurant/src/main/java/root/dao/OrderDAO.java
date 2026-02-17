package root.dao;

public interface OrderDAO {
    boolean createOrUpdateOrderItem(Integer orderId, Integer foodId);
}
