package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ivan", "Pahomov", (byte) 33);
        userService.saveUser("Viktor", "Gromyako", (byte) 28);
        userService.saveUser("Irina", "Visokaya", (byte) 19);
        userService.saveUser("Vasiliy", "Pistoletov", (byte) 66);
        for(User user : userService.getAllUsers()){
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
