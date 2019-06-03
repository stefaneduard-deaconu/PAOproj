package model;

public class User {
    private Integer userId, readerId, writerId;
    private String name;
    private transient String pass;


    public User(Integer userId, Integer readerId, Integer writerId,
                String name, String pass) {
        this.userId = userId;
        this.readerId = readerId;
        this.writerId = writerId;
        this.name = name;
        this.pass = pass;
    }
    public User(Integer userId, Integer readerId, Integer writerId) {
        this.userId = userId;
        this.readerId = readerId;
        this.writerId = writerId;
    }
    public User(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getReaderId() {
        return readerId;
    }

    public void setReaderId(Integer readerId) {
        this.readerId = readerId;
    }

    public Integer getWriterId() {
        return writerId;
    }

    public void setWriterId(Integer writerId) {
        this.writerId = writerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
