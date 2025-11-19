package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {

    private static final String USER_NAME = "root";
    private static final String PASSWORD = "0n1oh";
    private static final String URL = "jdbc:mysql://localhost:3306/training";
    private static SessionFactory sessionFactory;
    private static final Logger logger = Logger.getLogger(Util.class.getName());

    public static Connection getConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            logger.info("Подключение к базе успешно");
            return con;
        } catch (ClassNotFoundException e) {
            logger.severe("Драйвер не найден");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            logger.severe("Ошибка подключения к базе данных");
            throw new RuntimeException(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {

            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/training");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "0n1oh");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);

                sessionFactory = configuration.buildSessionFactory();
                logger.info("Сессия создана успешно");
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Ошибка при создании сессии", e);
            }
        }
        return sessionFactory;
    }

    public static void closing() {
        if (sessionFactory != null) {
            sessionFactory.close();
            logger.info("Сессия закрыта");
        }
    }
}



