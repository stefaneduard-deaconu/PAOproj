package model.checker;

public class FindEnd {
    Integer findEndId, writerId, end;

    public FindEnd(Integer findEndId) {
        this.findEndId = findEndId;
    }

    public FindEnd(Integer findEndId, Integer writerId) {
        this.findEndId = findEndId;
        this.writerId = writerId;
    }
    public FindEnd(Integer findEndId, Integer writerId, Integer endLocation) {
        this.findEndId = findEndId;
        this.writerId = writerId;
        end = endLocation;
    }


    public Integer getFindEndId() {
        return findEndId;
    }

    public void setFindEndId(Integer findEndId) {
        this.findEndId = findEndId;
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
