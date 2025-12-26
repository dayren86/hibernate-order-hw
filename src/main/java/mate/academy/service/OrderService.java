package mate.academy.service;

import java.util.List;
import mate.academy.model.Orders;
import mate.academy.model.ShoppingCart;
import mate.academy.model.User;

public interface OrderService {
    Orders completeOrder(ShoppingCart shoppingCart);
    
    List<Orders> getOrdersHistory(User user);
}
