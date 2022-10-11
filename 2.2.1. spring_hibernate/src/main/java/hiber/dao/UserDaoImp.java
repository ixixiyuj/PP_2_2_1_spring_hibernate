package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      Session session = sessionFactory.getCurrentSession();
      session.save(user);
   }
   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession()
              .createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getCarOwner(String model, int series) {
      Session session = sessionFactory.openSession();
      String hql = "from User u where car.model = :param1 and car.series = :param2";
      TypedQuery<User> query = session.createQuery(hql)
              .setParameter("param1", model)
              .setParameter("param2", series);
      return query.getResultList().get(0);

   }



}
