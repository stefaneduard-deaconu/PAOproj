package model;

public class AutoIndex {
    // generate an upcoming id
    protected Integer nextId = 1;
    public Integer nextId() {
        return nextId++;
    }
    public void setNextId(Integer id) { // we set a next id, for when we read from a .csv
        nextId = id;
    }
    public Integer getNextId() {
        return nextId;
    }
}
