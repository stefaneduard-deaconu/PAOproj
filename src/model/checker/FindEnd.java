package model.checker;

public class FindEnd {
    Integer id, writerId, end;

    public FindEnd(Integer id) {
        this.id = id;
    }

    public FindEnd(Integer id, Integer writerId) {
        this.id = id;
        this.writerId = writerId;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWriterId() {
        return writerId;
    }

    public void setWriterId(Integer writerId) {
        this.writerId = writerId;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }
}
