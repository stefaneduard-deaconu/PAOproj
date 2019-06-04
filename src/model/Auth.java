package model;

import java.util.List;

// Stage 2 ----------------------------------------------------------------------
//        1. Extindeti proiectul din prima etapa prin realizarea persistentei
//           utilizând fisiere.bse vor realiza fisiere de tip csv pentru cel
//           putin 4 dintre clasele definite in prima etapa:
//
//        - se vor realiza servicii generice pentru scrierea si citirea
//              din fi?iere
//        - la pornirea programului se vor încarca datele din fisiere utilizând
//              serviciile
//
//
//        2. Realizarea unui serviciu de audit
//
//        - se va realiza un serviciu care sa scrie într-un fisier de tip CSV
//              de fiecare data când este executata una dintre acsiunile descrise
//              in prima etapa. Structura fisierului: timestamp; actiune - Logger
// ------------------------------------------------------------------------------
public class Auth {
    List<User> users;

    private static Auth ourInstance = new Auth();
    public static Auth getInstance() {
        return ourInstance;
    }
    private Auth() { // read users
    }

    public void addUser(User newUser) {
        users.add(newUser);
    }
    public void addUsers(List<User> newUsers) {
        newUsers.forEach((user) -> users.add(user));
    }

    public static void main(String[] args) {
        Auth instance = Auth.getInstance();
    }
}
