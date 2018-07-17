package training.caboose.caboose.Models;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class PositionIndex {
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(String assignedDate) {
        this.assignedDate = assignedDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    private String uid;
    private String assignedDate;
    private String id;
    private int index;


    public PositionIndex(String uid, String assignedDate, String id, int index) {
        this.uid = uid;
        this.assignedDate = assignedDate;
        this.id = id;
        this.index = index;
    }

    public PositionIndex() {
    }

}
