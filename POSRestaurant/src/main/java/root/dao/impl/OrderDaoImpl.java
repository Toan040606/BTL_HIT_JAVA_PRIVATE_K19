package root.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import root.constant.QuerryMessage;
import root.dao.OrderDAO;
import root.model.entity.core.Order;
import root.model.entity.menu.Food;
import root.model.entity.order.detail.OrderItem;
import root.util.ConnectDB;

public class OrderDaoImpl implements OrderDAO {
    ConnectDB connectDB = new ConnectDB();
    @Override
    public boolean createOrUpdateOrderItem(Integer orderId,Integer foodId){
        Transaction transaction = null;
        try(Session session = connectDB.open()) {
            transaction = session.beginTransaction();

            Order order = session.get(Order.class,orderId);
            Food food = session.get(Food.class, foodId);

            if(order == null || food == null){
                transaction.rollback();
                return false;
            }
            OrderItem existingItem = session.createQuery(QuerryMessage.EXISTING_ITEM, OrderItem.class)
                    .setParameter("orderId",orderId)
                    .setParameter("foodId", foodId)
                    .uniqueResult();

            if(existingItem != null) {

                existingItem.setQuantity(existingItem.getQuantity() + 1);

            }else{
                OrderItem newItem = new OrderItem();
                newItem.setOrder(order);
                newItem.setFood(food);
                newItem.setQuantity(1);
                newItem.setPriceAtOrder(food.getPrice());

                session.persist(newItem);
            }

            transaction.commit();
            return true;
        } catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }

            e.printStackTrace();
            return false;
        }

    }
}
