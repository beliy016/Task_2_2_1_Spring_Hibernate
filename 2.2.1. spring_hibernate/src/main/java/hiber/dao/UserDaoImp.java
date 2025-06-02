package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getByCar(String model, int series) {
      String HQL = "FROM User u WHERE u.car.model=:model and u.car.series=:series";
      List<User> list = sessionFactory.getCurrentSession().createQuery(HQL, User.class)
              .setParameter("model", model).setParameter("series", series).getResultList();
      if (list.size() != 0) {
         return list.get(0);
      } else {
         return null;
      }
   }
}
