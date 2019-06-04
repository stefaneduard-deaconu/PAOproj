package model;

import java.util.List;

public class Auth {
    List<User> users;

    private static Auth ourInstance = new Auth(new DB());
    public static Auth getInstance() {
        return ourInstance;
    }

    public void addUser(User newUser) {
        users.add(newUser);
    }
    private Auth(DB database) {
        users = db.getUsers(); // load from JDBC
    }
}
