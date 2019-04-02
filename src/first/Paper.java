package first;

public class Paper {
    private int pages;
    private Double grade;
    public Paper() {

    }
    public Paper(Integer pages, Double grade) {
        this.pages = pages;
        this.grade = grade;
    }
}

class Book extends Paper {
    private String primaryAuthor;
    public Book(String author) {
        primaryAuthor = new String(author);
    }
}

class Article extends Paper {
    private String []authors; // I know the intel is Bad, I just want something different inside this class
    public Article(String[] auths) { // deep copy
        authors = new String[auths.length];
        for (int i = 0; i < authors.length; i++)
            authors[i] = new String(auths[i]);
    }
}