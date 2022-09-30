package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        SessionFactory sessionFactory = new Configuration().setProperties(Util.getProperties()).addAnnotatedClass(User.class).addProperties(Util.getProperties()).buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS User(id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,name VARCHAR(100) NOT NULL ,lastname VARCHAR(100) NOT NULL,age TINYINT NOT NULL)").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException(e);
        }
        finally {
            sessionFactory.close();
        }
    }

    @Override
    public void dropUsersTable() {
        SessionFactory sessionFactory = new Configuration().setProperties(Util.getProperties()).addAnnotatedClass(User.class).buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS User").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException(e);
        }
        finally {
            sessionFactory.close();
        }
    }




    @Override
    public void saveUser(String name, String lastName, byte age) {
        SessionFactory sessionFactory = new Configuration().setProperties(Util.getProperties()).addAnnotatedClass(User.class).buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            session.getTransaction().commit();
        }catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException(e);

        }finally{
            sessionFactory.close();

        }

    }

    @Override
    public void removeUserById(long id) {
        SessionFactory sessionFactory = new Configuration().setProperties(Util.getProperties()).addAnnotatedClass(User.class).buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            User user = session.get(User.class,id);
            session.remove(user);
            session.getTransaction().commit();
        }catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException(e);

        }finally{
            sessionFactory.close();

        }


    }

    @Override
    public List<User> getAllUsers() {
        SessionFactory sessionFactory = new Configuration().setProperties(Util.getProperties()).addAnnotatedClass(User.class).buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            List <User> all = session.createQuery("FROM User").getResultList();
            session.getTransaction().commit();
            return all;
        }catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException(e);

        }finally {
            sessionFactory.close();
        }

    }

    @Override
    public void cleanUsersTable() {
        SessionFactory sessionFactory = new Configuration().setProperties(Util.getProperties()).addAnnotatedClass(User.class).buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            session.createQuery("DELETE User").executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException(e);

        }finally {
            sessionFactory.close();
        }

    }
}
