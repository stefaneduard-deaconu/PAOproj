package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Auth;
import model.User;
import model.machine.Reader;
import model.machine.Writer;
import model.paper.Paper;
import model.paper.Papermark;

import java.sql.*;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;

public class DB {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "java.sql.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/";

    //  Database credentials
    static final String USER = "user";
    static final String PASS = "pass";

    // fields to use for the model, like prepared statements and a connection
    private Connection connection = null;
    private PreparedStatement isUserPST = null;
    private PreparedStatement isUsernameTakenPST = null;

    public static String getJdbcDriver() {
        return JDBC_DRIVER;
    }

    public static String getDbUrl() {
        return DB_URL;
    }

    public static String getUSER() {
        return USER;
    }

    public static String getPASS() {
        return PASS;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public PreparedStatement getIsUserPST() {
        return isUserPST;
    }

    public void setIsUserPST(PreparedStatement isUserPST) {
        this.isUserPST = isUserPST;
    }

    public PreparedStatement getIsUsernameTakenPST() {
        return isUsernameTakenPST;
    }

    public void setIsUsernameTakenPST(PreparedStatement isUsernameTakenPST) {
        this.isUsernameTakenPST = isUsernameTakenPST;
    }

    public void initialise() {
        connection = null;

        try {
            // STEP 2: Register JDBC driver
            Class.forName("java.sql.Driver");

            // STEP 3: Open a connection
            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        }catch(SQLException se){ // Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){ // Handle errors for Class.forName
            e.printStackTrace();
        }finally{ // after we opened the connection:
            // prepare statements:
            try {
                isUserPST = connection.prepareStatement("SELECT count(*) from USERS WHERE name = ? AND pass = ?");
                isUsernameTakenPST = connection.prepareStatement("SELECT count(*) FROM USERS WHERE name = ?");
            } catch (SQLException se) { // handle errors for the statement
                se.printStackTrace();
            }catch(Exception e){ // Handle errors for Class.forName
                e.printStackTrace();
            }
        }//end try
        System.out.println("Database connection created");
    }

    public Boolean isUser(String name, String pass) {
        try {
            isUserPST.setString(0, name);
            isUserPST.setString(1, pass);
            ResultSet rs = isUserPST.executeQuery();
            Integer count = rs.getInt(0); // only one value of one column returned
            return count == 1;
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return false;
        }
    }
    public Boolean isUsernameTaken(String name) {
        try {
            isUsernameTakenPST.setString(0, name);
            ResultSet rs = isUsernameTakenPST.executeQuery();
            Integer count = rs.getInt(0); // only one value of one column returned
            return count > 1;
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return false;
        }
    }
    public List<User> getUsers() {
        List<User> list = new ArrayList<>();
        // I won't use a preparedStatement, because we ought to only do this rarely, it is too general an operation
        try {
            Statement getUsersST = connection.createStatement();
            ResultSet rs = getUsersST.executeQuery("SELECT * FROM USERS");
            String name, pass;
            Integer userId, readerId, writerId;
            while(rs.next()) {
                userId = rs.getInt("userId");
                readerId = rs.getInt("readerId");
                writerId = rs.getInt("writerId");
                name = rs.getString("name");
                pass = rs.getString("pass");
                list.add(new User(
                   userId, readerId, writerId, name, pass
                ));
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // User CRUD
    public Boolean createUser(User newUser) { // we add the new User to Auth to respect the implementation
        try {
            PreparedStatement pst = connection.prepareStatement("INSERT INTO USERS(userId, readerId, writerId, name, pass) " +
                    "VALUES (?, ?, ?, ?, ?)");
            pst.setInt(1, newUser.getUserId());
            pst.setInt(2, newUser.getReaderId());
            pst.setInt(3, newUser.getWriterId());
            pst.setString(4, newUser.getName());
            pst.setString(5, newUser.getPass());
            pst.executeUpdate();
            Auth.getInstance().addUser(newUser); // just to respect the Auth implementation
            return true;

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return false;
        }
    }
    public User readUser(Integer userId) {
        User user = new User(userId);
        try {
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM USERS WHERE userId = ?");
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();
            user.setReaderId(rs.getInt("readerId"));
            user.setWriterId(rs.getInt("writerId"));
            user.setName(rs.getString("name"));
            user.setPass(rs.getString("pass"));
            return user;
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public Boolean updateUser(User updatedUser) {
        try {
            PreparedStatement pst = connection.prepareStatement("UPDATE USERS SET readerId = ?, writerId = ?, name = ?, pass = ?" +
                    "WHERE userId = ?");
            pst.setInt(1, updatedUser.getReaderId());
            pst.setInt(2, updatedUser.getWriterId());
            pst.setString(3, updatedUser.getName());
            pst.setString(4, updatedUser.getPass());
            pst.setInt(5, updatedUser.getUserId());
            pst.executeUpdate();
            return true;

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return false;
        }
    }
    public Boolean deleteUser(Integer userId) {
        try {
            PreparedStatement pst = connection.prepareStatement("DELETE FROM USERS WHERE userId = ?");
            pst.setInt(1, userId);
            pst.executeUpdate();
            return true;
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return false;
        }
    }

    // PertainsTo CRUD


    // Papermarks CRUD
    public Boolean createPapermarks(Papermark newPapermark) {
        try {
            PreparedStatement pst = connection.prepareStatement("INSERT INTO PAPERMARKS(papermarkId, paperId, mark) " +
                    "VALUES (?, ?, ?)");
            pst.setInt(1, newPapermark.getPapermarkId());
            pst.setInt(2, newPapermark.getPaperId());
            pst.setString(3, newPapermark.getMark());
            pst.executeUpdate();
            return true;

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return false;
        }
    }
    public Papermark readPapermark(Integer papermarkId) {
        Papermark papermark = new Papermark(papermarkId);
        try {
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM PAPERMARKS WHERE papermarkId = ?");
            pst.setInt(1, papermarkId);
            ResultSet rs = pst.executeQuery();
            papermark.setPapermarkId(rs.getInt("papermarkId"));
            papermark.setPaperId(rs.getInt("paperId"));
            papermark.setMark(rs.getString("mark"));
            return papermark;
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public Boolean updatePapermark(Papermark updatedPapermark) {
        try {
            PreparedStatement pst = connection.prepareStatement("UPDATE PAPERMARKS SET papermarkId = ?, paperId = ?, mark = ?" +
                    "WHERE papermarkId = ?");
            pst.setInt(1, updatedPapermark.getPapermarkId());
            pst.setInt(2, updatedPapermark.getPaperId());
            pst.setString(3, updatedPapermark.getMark());
            pst.executeUpdate();
            return true;

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return false;
        }
    }
    public Boolean deletePapermark(Integer papermarkId) {
        try {
            PreparedStatement pst = connection.prepareStatement("DELETE FROM PAPERMARKS WHERE papermarkId = ?");
            pst.setInt(1, papermarkId);
            pst.executeUpdate();
            return true;
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return false;
        }
    }
    public List<Papermark> getPapermarkByPaperIdUserId(Integer paperId, Integer userId) {
        List<Papermark> list = null;
        try {
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM PAPERMARKS WHERE paperId = ?");
            pst.setInt(1, paperId);
            ResultSet rs = pst.executeQuery();
            Integer papermarkId;
            while(rs.next()) {
                papermarkId = rs.getInt("papermarkId");
                // for this papermark, find the corresponding user
                PreparedStatement userFilterPST = connection.prepareStatement("SELECT * FROM PERTAINSTO " +
                        "WHERE papermarkId = ?");
                ResultSet ptRs = userFilterPST.executeQuery();
                // we know this papermark must exist:
                if (userId == ptRs.getInt("userId"))
                    list.add(new Papermark(
                            papermarkId, paperId, rs.getString("mark")
                    ));
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return null;
        }
    }

    // Paper CRUD
    public Boolean createPaper(Paper newPaper) {
        try {
            PreparedStatement pst = connection.prepareStatement("INSERT INTO PAPERS(paperId, papermarkId) " +
                    "VALUES (?, ?)");
            pst.setInt(1, newPaper.getPaperId());
            pst.setInt(2, newPaper.getPapermarkId());
            pst.executeUpdate();
            return true;

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return false;
        }
    }
    public Paper readPaper(Integer paperId) {
        Paper paper = new Paper(paperId);
        try {
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM PAPERS WHERE paperId = ?");
            pst.setInt(1, paperId);
            ResultSet rs = pst.executeQuery();
            paper.setPapermarkId(rs.getInt("papermarkId"));
            paper.setText(rs.getString("name"));
            return paper;
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public Boolean updatePaper(Paper updatedPaper) {
        try {
            PreparedStatement pst = connection.prepareStatement("UPDATE PAPERS SET paperId = ?, writerId = ?, name = ?, pass = ?" +
                    "WHERE userId = ?");
            pst.setInt(1, updatedPaper.getPaperId());
            pst.setInt(2, updatedPaper.getPapermarkId());
            pst.setString(3, updatedPaper.getText());
            pst.executeUpdate();
            return true;
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return false;
        }
    }
    public Boolean deletePaper(Integer paperId) {
        try {
            PreparedStatement pst = connection.prepareStatement("DELETE FROM PAPERS WHERE paperId = ?");
            pst.setInt(1, paperId);
            pst.executeUpdate();
            return true;
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return false;
        }
    }
    public ObservableList<String> getPapersObservable() {
        ObservableList<String> list = FXCollections.observableArrayList(); // this is a factory
        // I won't use a preparedStatement, because we ought to only do this rarely, it is too general an operation
        try {
            Statement getTextsST = connection.createStatement();
            ResultSet rs = getTextsST.executeQuery("SELECT text FROM PAPERS");
            while(rs.next()) {
                list.add(rs.getString("text"));
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Reader CRUD
    public Boolean createReader(Reader newReader) {
        try {
            PreparedStatement pst = connection.prepareStatement("INSERT INTO READERS(readerId, userId, paperId) " +
                    "VALUES (?, ?, ?)");
            pst.setInt(1, newReader.getUserId());
            pst.setInt(2, newReader.getReaderId());
            pst.setInt(3, newReader.getPaperId());
            pst.executeUpdate();
            return true;

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return false;
        }
    }
    public Reader readReader(Integer readerId) {
        Reader reader = new Reader(readerId);
        try {
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM READERS WHERE readerId = ?");
            pst.setInt(1, readerId);
            ResultSet rs = pst.executeQuery();
            reader.setReaderId(rs.getInt("readerId"));
            reader.setUserId(rs.getInt("userId"));
            reader.setPaperId(rs.getInt("paperId"));
            return reader;
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public Boolean updateReader(Reader updatedReader) {
        try {
            PreparedStatement pst = connection.prepareStatement("UPDATE READERS SET readerId = ?, userId = ?, paperId = ?" +
                    "WHERE userId = ?");
            pst.setInt(1, updatedReader.getReaderId());
            pst.setInt(2, updatedReader.getUserId());
            pst.setInt(3, updatedReader.getPaperId());

            pst.executeUpdate();
            return true;

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return false;
        }
    }
    public Boolean deleteReader(Integer readerId) {
        try {
            PreparedStatement pst = connection.prepareStatement("DELETE FROM READERS WHERE readerId = ?");
            pst.setInt(1, readerId);
            pst.executeUpdate();
            return true;
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return false;
        }
    }

    // PertainsTo CRUD, called when creating a papermark

    // Writer CRUD
    public Boolean createWriter(Writer newWriter) {
        try {
            PreparedStatement pst = connection.prepareStatement("INSERT INTO WRITERS(writerId, userId, paperId, findEndId) " +
                    "VALUES (?, ?, ?, ?)");
            pst.setInt(1, newWriter.getWriterId());
            pst.setInt(2, newWriter.getUserId());
            pst.setInt(3, newWriter.getPaperId());
            pst.setInt(4, newWriter.getFindEndId());
            pst.executeUpdate();
            return true;

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return false;
        }
    }
    public Writer readWriter(Integer writerId) {
        Writer writer = new Writer(writerId);
        try {
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM WRITERS WHERE writerId = ?");
            pst.setInt(1, writerId);
            ResultSet rs = pst.executeQuery();
            writer.setWriterId(rs.getInt("writerId"));
            writer.setUserId(rs.getInt("userId"));
            writer.setPaperId(rs.getInt("paperId"));
            writer.setFindEndId(rs.getInt("fileEndId"));
            return writer;
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public Boolean updateWriter(Writer updatedWriter) {
        try {
            PreparedStatement pst = connection.prepareStatement("UPDATE WRITERS SET writerId = ?, userId = ?, paperId = ?, findEndId = ?) " +
                    "WHERE userId = ?");
            pst.setInt(1, updatedWriter.getWriterId());
            pst.setInt(2, updatedWriter.getUserId());
            pst.setInt(3, updatedWriter.getPaperId());
            pst.setInt(4, updatedWriter.getFindEndId());
            pst.executeUpdate();
            return true;

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return false;
        }
    }
    public Boolean deleteWriter(Integer writerId) {
        try {
            PreparedStatement pst = connection.prepareStatement("DELETE FROM WRITERS WHERE writerId = ?");
            pst.setInt(1, writerId);
            pst.executeUpdate();
            return true;
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return false;
        }
    }


    public void close() {
        // here I'll just close all connections and statements

    }

    public static void main(String[] args) {
        DB db = new DB();


        db.initialise();


        Connection connection = db.getConnection();
        System.out.println(connection);

        try {
            Statement st = connection.createStatement();
            st.executeQuery("CREATE DATABASE AMNE");
        } catch (SQLException se) {
            se.printStackTrace();
        }

        System.out.println("before user creation");
        db.createUser(new User(
           1, 1, 1, "user1", "pass"
        ));



    }
}
