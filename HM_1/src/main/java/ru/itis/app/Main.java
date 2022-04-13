package ru.itis.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.itis.dao.UserRepositoryImpl;
import ru.itis.models.User;

public class Main {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        UserRepositoryImpl userRepository = new UserRepositoryImpl(session);

        //System.out.println(userRepository.findById(1L));

        //System.out.println(userRepository.findAll());

        User user = User.builder()
                .email("e")
                .build();

        user = userRepository.save(user);
        System.out.println(user);

        userRepository.delete(4L);

        sessionFactory.close();
        session.close();

    }

}
