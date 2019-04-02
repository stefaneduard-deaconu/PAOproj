package first;

import java.util.Random;

public class Writer implements Comparable<Writer>{ // a writer is a person who writes words, of any kind
    private Random idGetter = new Random();
    private Integer id;
    Double grade;
    private String name;
    public Writer(String name) {
        this.name = name;
        id = idGetter.nextInt();
    }
    public Integer getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public void evaluate(Double grade) {
        this.grade = grade;
    }

    @Override
    public int compareTo(Writer otherWriter) {
        if (this.grade > otherWriter.grade)
            return 1;
        if (this.grade < otherWriter.grade)
            return -1;
        return 0;
    }
    // in the future: an evaluate function ot grade him
}
