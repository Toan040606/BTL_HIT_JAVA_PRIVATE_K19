package root.service.impl;

import root.constant.ErrorMessage;
import root.constant.SuccessMessage;
import root.dao.OrderDAO;
import root.dao.impl.OrderDaoImpl;
import root.service.OrderService;

public class OrderServiceImpl implements OrderService {
    OrderDAO orderDAO = new OrderDaoImpl();

    @Override
    public void createFoodByQr(Integer orderId, Integer foodId){
        if(orderId == null || foodId == null){
            System.out.println(ErrorMessage.ADD_FOOD_EMPTY_ERROR);
            return;
        }

        if(orderDAO.createOrUpdateOrderItem(orderId,foodId)){
            System.out.println(SuccessMessage.ADD_FOOD_SUCCESS);
        } else {
            System.out.println(ErrorMessage.ADD_FOOD_ERROR);
        }
    }
}
