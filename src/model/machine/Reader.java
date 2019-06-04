package model.machine;


public class Reader{
    Integer readerId, userId, paperId;

    public Reader(Integer id) {
        readerId = id;

    }
    public Reader(Integer readerId, Integer userId) {
        this.readerId = readerId;
        this.userId = userId;
        this.paperId = null;
    }
    public Reader(Integer readerId, Integer userId, Integer paperId) {
        this.readerId = readerId;
        this.userId = userId;
        this.paperId = paperId;
    }

    public Integer getReaderId() {
        return readerId;
    }

    public void setReaderId(Integer readerId) {
        this.readerId = readerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }
}
