package model.paper;


public class Paper {
    Integer paperId;
    String text;

    public Paper(Integer paperId) {
        this.paperId = paperId;
    }
    public Paper(Integer paperId, String textString) {
        this.paperId = paperId;
        this.text = textString;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}
