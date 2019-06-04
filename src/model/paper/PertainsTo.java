package model.paper;


import java.util.HashMap;
import java.util.Map;

public class PertainsTo {
    private Integer pertainsToId;
    private static Map<Integer, Integer> papermarkToUser;

    // Singleton
    private static PertainsTo ourInstance = new PertainsTo();
    public static PertainsTo getInstance() {
        return ourInstance;
    }
    private PertainsTo() {
        papermarkToUser = new HashMap<>();
    }

    // getters and setters
    public Integer getPertainsToId() {
        return pertainsToId;
    }
    public void setPertainsToId(Integer pertainsToId) {
        this.pertainsToId = pertainsToId;
    }


    public static void addRelation(Integer papermarkId, Integer userId) {
        papermarkToUser.put(papermarkId, userId);
    }
    public static void addRelations(Map<Integer, Integer> papermarkToUserRelations) {
        papermarkToUser.putAll(papermarkToUserRelations);
    }
    public static Map<Integer, Integer> getRelations() {
        return papermarkToUser;
    }
}
