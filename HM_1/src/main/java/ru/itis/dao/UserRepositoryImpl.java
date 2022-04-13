package ru.itis.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.itis.models.User;

import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements CrudRepository<User, Long> {

    private final Session session;

    public UserRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(session.get(User.class, id));
    }

    @Override
    public List<User> findAll() {
        //HQL
        //select * from users
        String hql = "from User";
        Query<User> query = session.createQuery(hql);
        return query.getResultList();
    }

    @Override
    public User save(User item) {
        session.getTransaction().begin();
        session.persist(item);
        session.getTransaction().commit();
        return item;
    }

    @Override
    public void delete(Long id) {
        try {
            session.getTransaction().begin();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

}
