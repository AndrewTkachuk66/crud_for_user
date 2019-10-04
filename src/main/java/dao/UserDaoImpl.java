package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import model.User;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.logging.Logger;

@Repository
public class UserDaoImpl implements UserDao {
    //private static final Logger logger = (Logger) LoggerFactory.getLogger(UserDaoImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> listUsers() {
        Session session = sessionFactory.getCurrentSession();
        List<User> usersList = session.createQuery("from User").list();
        for(User user : usersList){
           // logger.info("Person List::"+user);
        }
        return usersList;
    }

    @Override
    public void addUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
    }


    @Override
    public void deleteUser(String id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, id);
        if(user != null){
            session.delete(user);
        }
    }

    @Override
    public void updateUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Override
    public User getUserById(String id) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, id);
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.createQuery("FROM User WHERE email like :email").setString("email",email).uniqueResult();
        return user;
    }
}
