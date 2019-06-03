package model;

import database.DB;

import java.sql.PreparedStatement;
import java.util.LinkedList;
import java.util.List;

public class Auth {
    List<User> users;
    DB db;

    private static Auth ourInstance = new Auth(new DB());
    public static Auth getInstance() {
        return ourInstance;
    }

    public void addUser(User newUser) {
        users.add(newUser);
    }
    private Auth(DB database) {
        db = database;
        users = db.getUsers(); // load from JDBC
    }
}
