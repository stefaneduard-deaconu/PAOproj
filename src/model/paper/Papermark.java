package model.paper;

import java.util.Map;

public class Papermark {
    private static Integer nextId = 1;
    Integer papermarkId;
    String mark;


    public Papermark() {
        mark = "";
    }
    public Papermark(String markString) {
        mark = markString;
    }


    public String getMark() {
        return mark;
    }
    public void setMark(String mark) {
        this.mark = mark;
    }
    public Integer getPapermarkId() {
        return papermarkId;
    }
    public void setPapermarkId(Integer papermarkId) {
        this.papermarkId = papermarkId;
    }

    // generate an upcoming id
    public Integer nextId() {
        return nextId++;
    }
    public void setNextId(Integer id) { // we set a next id, for when we read from a .csv
        nextId = id;
    }
}
