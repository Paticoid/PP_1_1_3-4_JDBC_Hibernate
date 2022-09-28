package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Leonardo","Leo",(byte)15);
        userService.saveUser("Raphael", "Raph",(byte)15);
        userService.saveUser("Michelangelo","Miki",(byte)15);
        userService.saveUser("Donatello", "Don",(byte)15);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
