package training.caboose.caboose.Models;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Answer {
    private Boolean marked;
    private String text;
    private String uid;

    public Answer() {
    }

    public Answer(Boolean marked, String text, String uid) {
        this.marked = marked;
        this.text = text;
        this.uid = uid;
    }

    public Boolean getMarked() {
        return marked;
    }

    public void setMarked(Boolean marked) {
        this.marked = marked;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
