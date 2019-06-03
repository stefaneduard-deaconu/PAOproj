package model.paper;


public class PertainsTo {
    Integer pertainsToId, userId, paperId;

    public PertainsTo(Integer pertainsToId, Integer userId, Integer paperId) {
        this.pertainsToId = pertainsToId;
        this.userId = userId;
        this.paperId = paperId;
    }

    public Integer getPertainsToId() {
        return pertainsToId;
    }

    public void setPertainsToId(Integer pertainsToId) {
        this.pertainsToId = pertainsToId;
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
