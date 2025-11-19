package jm.task.core.jdbc;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

//        UserService userService = new UserServiceImpl(new UserDaoJDBCImpl());
//
//        userService.createUsersTable();
//
//        userService.saveUser("Name1","LastName1",(byte)34);
//        userService.saveUser("Name2","LastName2",(byte)37);
//        userService.saveUser("Name3","LastName3",(byte)38);
//        userService.saveUser("Name4","LastName4",(byte)39);
//
//        userService.removeUserById(1);
//        userService.getAllUsers();
//        userService.cleanUsersTable();
//        userService.dropUsersTable();

        UserService userService1 = new UserServiceImpl(new UserDaoHibernateImpl());

        userService1.createUsersTable();
        userService1.saveUser("Name5", "Lastname5", (byte)55);
        userService1.getAllUsers().forEach(System.out::println);
        userService1.createUsersTable();
        userService1.dropUsersTable();
    }
}
