package mate.academy.dao;

import java.util.List;
import mate.academy.model.Orders;
import mate.academy.model.User;

public interface OrderDao {
    Orders add(Orders orders);

    List<Orders> getByUser(User user);
}
