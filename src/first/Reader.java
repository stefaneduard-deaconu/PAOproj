package first;

public class Reader {
    private SpeedReader mentor;
    private String name;
    public Reader(String name, SpeedReader initialMentor) {
        this.name = name;
        mentor = initialMentor;
    }
    public Reader(String name) { // even if it's more logical to start with a mentor
        this.name = name;
        mentor = null;
    }
    public void setMentor(SpeedReader sr) {
        mentor = sr; // aggregation
    }
    public SpeedReader getMentor() {
        return mentor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
