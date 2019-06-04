package model.machine;


import java.io.IOException;

public class Writer{
    Integer writerId, userId, paperId, findEndId;


    public Writer(Integer id) {
        writerId = id;

    }
    public Writer(Integer readerId, Integer userId) {
        this.writerId = readerId;
        this.userId = userId;
    }
    public Writer(Integer writerId, Integer userId, Integer paperId, Integer findEndId) {
        this.writerId = writerId;
        this.userId = userId;
        this.paperId = paperId;
        this.findEndId = findEndId;
    }

    public Integer getWriterId() {
        return writerId;
    }

    public void setWriterId(Integer writerId) {
        this.writerId = writerId;
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

    public Integer getFindEndId() {
        return findEndId;
    }

    public void setFindEndId(Integer findEndId) {
        this.findEndId = findEndId;
    }

}
