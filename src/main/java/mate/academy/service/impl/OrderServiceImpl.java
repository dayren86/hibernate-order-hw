package mate.academy.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import mate.academy.dao.OrderDao;
import mate.academy.lib.Inject;
import mate.academy.lib.Service;
import mate.academy.model.Orders;
import mate.academy.model.ShoppingCart;
import mate.academy.model.User;
import mate.academy.service.OrderService;
import mate.academy.service.ShoppingCartService;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;

    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public Orders completeOrder(ShoppingCart shoppingCart) {
        Orders orders = new Orders();
        orders.setTickets(shoppingCart.getTickets());
        orders.setUser(shoppingCart.getUser());
        orders.setOrderDate(LocalDateTime.now());

        Orders add = orderDao.add(orders);

        shoppingCartService.clearShoppingCart(shoppingCart);

        return add;
    }

    @Override
    public List<Orders> getOrdersHistory(User user) {
        return orderDao.getByUser(user);
    }
}
