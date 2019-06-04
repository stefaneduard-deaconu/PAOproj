package model.paper;

import java.util.Map;

public class Papermark {
    Integer papermarkId;
    String mark;


    public Papermark() {
        mark = "";
    }
    public Papermark(String markString) {
        mark = markString;
    }
    public Papermark(Integer papermarkId, String markString) {
        this.papermarkId = papermarkId;
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

}
