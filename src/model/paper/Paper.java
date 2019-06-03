package model.paper;

public class Paper {
    Integer paperId, papermarkId;
    String text;

    public Paper(Integer paperId) {
        this.paperId = paperId;
    }
    public Paper(Integer paperId, Integer papermarkId) {
        this.paperId = paperId;
        this.papermarkId = papermarkId;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public Integer getPapermarkId() {
        return papermarkId;
    }

    public void setPapermarkId(Integer papermarksId) {
        this.papermarkId = papermarkId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
