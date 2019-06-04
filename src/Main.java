import csv.GenericReader;
import model.Auth;
import model.User;
import model.checker.FindEnd;
import model.machine.Reader;
import model.machine.Writer;
import model.paper.Paper;
import model.paper.Papermark;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static List<List<String>> readUsers() { // in this implementation, we consider Auth as a singleton,
                                                   // that will retain all the current users, during runtime
        Auth auth = Auth.getInstance();
        // read users from csv:
        GenericReader<String> reader = new GenericReader<>("users.csv");
        return reader.getAllLines();
    }
    public static List<List<String>> readReaders() {
        GenericReader<String> reader = new GenericReader<>("readers.csv");
        return reader.getAllLines();
    }
    public static List<List<String>> readWriters() {
        GenericReader<String> reader = new GenericReader<>("writers.csv");
        return reader.getAllLines();
    }
    public static List<List<String>> readPapers() {
        GenericReader<String> reader = new GenericReader<>("papers.csv");
        return reader.getAllLines();
    }
    public static List<List<String>> readPapermarks() {
        GenericReader<String> reader = new GenericReader<>("papermarks.csv");
        return reader.getAllLines();
    }
    public static List<List<String>> readFindEnds() {
        GenericReader<String> reader = new GenericReader<>("finders.csv");
        return reader.getAllLines();
    }
    public static List<List<String>> readRelations() {
        GenericReader<String> reader = new GenericReader<>("relations.csv");
        return reader.getAllLines();
    }

    public static Set<User> getUsers() {
        Set<User> set = new LinkedHashSet<>();
        readUsers().forEach(
                (fields) -> {
                    User user = new User(
                            Integer.parseInt(fields.get(0)),
                            Integer.parseInt(fields.get(1)),
                            Integer.parseInt(fields.get(2)),
                            fields.get(3),
                            fields.get(4)
                    );
                    set.add(user);
                }
        );
        return set;
    }
    public static Set<Reader> getReaders() {
        Set<Reader> set = new LinkedHashSet<>();
        readReaders().forEach(
                (fields) -> {
                    Reader reader = new Reader(
                            Integer.parseInt(fields.get(0)),
                            Integer.parseInt(fields.get(1)),
                            Integer.parseInt(fields.get(2))
                    );
                    set.add(reader);
                }
        );
        return set;
    }
    public static Set<Writer> getWriters() {
        Set<Writer> set = new LinkedHashSet<>();
        readWriters().forEach(
                (fields) -> {
                    Writer writer = new Writer(
                            Integer.parseInt(fields.get(0)),
                            Integer.parseInt(fields.get(1)),
                            Integer.parseInt(fields.get(2)),
                            Integer.parseInt(fields.get(3))
                            );
                    set.add(writer);
                }
        );
        return set;
    }
    public static Set<Paper> getPapers() {
        Set<Paper> set = new LinkedHashSet<>();
        readPapers().forEach(
                (fields) -> {
                    Paper paper = new Paper(
                            Integer.parseInt(fields.get(0)),
                            fields.get(1)
                    );
                    set.add(paper);
                }
        );
        return set;
    }
    public static Set<FindEnd> getFindEnds() {
        Set<FindEnd> set = new LinkedHashSet<>();
        readFindEnds().forEach(
                (fields) -> {
                    FindEnd findEnd = new FindEnd(
                            Integer.parseInt(fields.get(0)),
                            Integer.parseInt(fields.get(1))
                    );
                    set.add(findEnd);
                }
        );
        return set;
    }
    public static Set<Papermark> getPapermarks() {
        Set<Papermark> set = new LinkedHashSet<>();
        readPapermarks().forEach(
                (fields) -> {
                    Papermark papermark = new Papermark(
                            Integer.parseInt(fields.get(0)),
                            fields.get(1)
                    );
                    set.add(papermark);
                }
        );
        return set;
    }
    public static Set<Map<Integer, Integer>> getRelations() {
        Set<Map<Integer, Integer>> set = new LinkedHashSet<>();
        readRelations().forEach(
                (fields) -> {
                    Map<Integer, Integer> relation = new LinkedHashMap<>(
                            Integer.parseInt(fields.get(0)),
                            Integer.parseInt(fields.get(1))
                    );
                    set.add(relation);
                }
        );
        return set;
    }

    public static void main(String[] args) {

        Set<User> userSet = getUsers();
        Set<Paper> paperSet = getPapers();
        Set<Reader> readerSet = getReaders();
        Set<Writer> writerSet = getWriters();
        Set<Papermark> papermarkSet = getPapermarks();
        Set<FindEnd> findEndSet = getFindEnds();
        Set<Map<Integer, Integer>> relationSet = getRelations();
        //
        System.out.println(userSet);
        System.out.println(paperSet);
        System.out.println(readerSet);
        System.out.println(writerSet);
        System.out.println(papermarkSet);
        System.out.println(findEndSet);
        System.out.println(relationSet);
        //
        // we create, read, update, or delete users
        // and we also create, read, update, or delete the corresponding objects (Readers and Writers, Papers, Papermarks, PertainsTo's
        Boolean dontStop = true;
        Scanner in = new Scanner(System.in);
        // create a logger:
        Logger logger = new Logger("logs.csv", false); // false means that we always append to the file
        while (dontStop) {
            System.out.print(
                    "\n\nPossible actions for user:\n" + "\t(1)Create one User\n" + "\t(2)Read one User\n" + "\t(3)Update one User\n" + "\t(4)Delete one User\n" +
                            "> Action: "
            );
            Integer action, userCount = 1;
            try {
                action = in.nextInt();
                        //
                Integer userId, readerId, writerId;
                String name, pass;
                Set<User> wantedUsers;
                Iterator<User> it;
                        //
                switch(action) {
                    case 1: // create
                        System.out.println("CREATE");
                        System.out.println("The user (userId, readerId, writerId, name, pass) - every field on a line :");
                        User user = new User(100 + userCount);
                        user.setUserId(Integer.parseInt(in.next()));
                        user.setReaderId(Integer.parseInt(in.next()));
                        user.setWriterId(Integer.parseInt(in.next()));
                        user.setName(in.next());
                        user.setPass(in.next());
                        userCount++;
                        logger.writeLog("CREATE", "User (extends Object)");
                        break;
                    case 2: // read
                        System.out.println("READ\n userId = ");
                        userId = in.nextInt();
                        wantedUsers = userSet.stream().filter((u) -> u.getUserId() == userId).collect(Collectors.toSet());
                        it = wantedUsers.iterator();
                        if (it.hasNext()) {
                            User wantedUser = it.next();
                            System.out.println("The user (userId, readerId, writerId, ) :");
                            System.out.println(wantedUser.getUserId());
                            System.out.println(wantedUser.getReaderId());
                            System.out.println(wantedUser.getWriterId());
                            System.out.println(wantedUser.getName());
                            System.out.println(wantedUser.getPass());
                            // and the logger action
                            logger.writeLog("READ", "User (extends Object)");
                        }
                        break;
                    case 3: // update
                        System.out.println("UPDATE\n userId = ");
                        userId = in.nextInt();
                        wantedUsers = userSet.stream().filter((u) -> u.getUserId() == userId).collect(Collectors.toSet());
                        it = wantedUsers.iterator();
                        if (it.hasNext()) {
                            User wantedUser = it.next();
                            System.out.println("The user (userId, readerId, writerId, name, pass) :");
                            System.out.println(wantedUser.getUserId());
                            System.out.println(wantedUser.getReaderId());
                            System.out.println(wantedUser.getWriterId());
                            System.out.println(wantedUser.getName());
                            System.out.println(wantedUser.getPass());
                            System.out.println("Modify by writing 'key value' pairs, each on one line, separeted by a whitespace ' '. Press Enter without inputing anything, to proceed");
                            String response = "";
                            while (response != "\n")
                            {
                                response = in.next();
                                if (response.split(" ")[0].equals("userId"))
                                    wantedUser.setUserId(Integer.parseInt(response.split(" ")[1]));
                                if (response.split(" ")[0].equals("readerId"))
                                    wantedUser.setReaderId(Integer.parseInt(response.split(" ")[1]));
                                if (response.split(" ")[0].equals("writerId"))
                                    wantedUser.setWriterId(Integer.parseInt(response.split(" ")[1]));
                                if (response.split(" ")[0].equals("name"))
                                    wantedUser.setName(response.split(" ")[1]);
                                if (response.split(" ")[0].equals("pass"))
                                    wantedUser.setPass(response.split(" ")[1]);
                            }
                        }
                        logger.writeLog("UPDATE", "User (extends Object)");
                    break;
                    case 4: // delete
                        System.out.println("DELETE\n userId = ");
                        userId = in.nextInt();
                        wantedUsers = userSet.stream().filter((u) -> u.getUserId() == userId).collect(Collectors.toSet());
                        it = wantedUsers.iterator();
                        if (it.hasNext())
                            userSet.remove(it.next());
                        logger.writeLog("DELETE", "User (extends Object)");
                        break;
                    default:
                        dontStop = false;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
