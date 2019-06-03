package model.paper;

import java.util.Map;

public class Papermark {
    Integer papermarkId, paperId;
    String mark;

    public Papermark(Integer papermarkId) {
        this.papermarkId = papermarkId;
    }
    public Papermark(Integer papermarkId, Integer paperId) {
        this.papermarkId = papermarkId;
        this.paperId = paperId;
    }
    public Papermark(Integer papermarkId, Integer paperId, String markString) {
        this.papermarkId = papermarkId;
        this.paperId = paperId;
        mark = markString;
    }

    public Integer getPapermarkId() {
        return papermarkId;
    }

    public void setPapermarkId(Integer papermarkId) {
        this.papermarkId = papermarkId;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
