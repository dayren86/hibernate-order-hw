package mate.academy.dao.impl;

import java.util.List;
import mate.academy.dao.OrderDao;
import mate.academy.exception.DataProcessingException;
import mate.academy.lib.Dao;
import mate.academy.model.Orders;
import mate.academy.model.User;
import mate.academy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class OrderDaoImpl implements OrderDao {
    @Override
    public Orders add(Orders orders) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(orders);
            transaction.commit();
            return orders;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert a orders: " + orders, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Orders> getByUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Orders> query = session.createQuery("FROM Orders sc "
                    + "LEFT JOIN FETCH sc.tickets t "
                    + "LEFT JOIN FETCH t.movieSession ms "
                    + "LEFT JOIN FETCH ms.movie "
                    + "LEFT JOIN FETCH ms.cinemaHall "
                    + "WHERE sc.user =:user", Orders.class);
            query.setParameter("user", user);
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find a orders by user: " + user, e);
        }
    }
}
