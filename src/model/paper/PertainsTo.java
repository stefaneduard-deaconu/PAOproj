package model.paper;


import java.util.HashMap;
import java.util.Map;

public class PertainsTo {
    Integer pertainsToId;
    Map<Integer, Integer> papermarkToUser;

    // Singleton
    private static PertainsTo ourInstance = new PertainsTo();
    public static PertainsTo getInstance() {
        return ourInstance;
    }
    private PertainsTo() {
        papermarkToUser = new HashMap<>();
    }

    //
    public Integer getPertainsToId() {
        return pertainsToId;
    }
    public void setPertainsToId(Integer pertainsToId) {
        this.pertainsToId = pertainsToId;
    }
    public Map<Integer, Integer> getPapermarkToUser() {
        return papermarkToUser;
    }
    public void setPapermarkToUser(Map<Integer, Integer> papermarkToUser) {
        this.papermarkToUser = papermarkToUser;
    }
}
