package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("name1", "lastname1", (byte) 32);
        userService.saveUser("name2", "lastname2", (byte) 27);
        userService.saveUser("name3", "lastname3", (byte) 54);
        userService.saveUser("name4", "lastname4", (byte) 14);

       for(User iter : userService.getAllUsers()) {
           System.out.println("User с именем - " + iter.getName() + " добавлен в базу данных");
       }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}

