package training.caboose.caboose.Models;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Question {
    private String text;
    private Map<String, Answer> Answers = new HashMap<>();
    private String uid;

    public Question(String text, Map<String, Answer> answers, String uid) {
        this.text = text;
        Answers = answers;
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Question{" +
                "text='" + text + '\'' +
                ", Answers=" + Answers +
                ", uid='" + uid + '\'' +
                '}';
    }

    public Question() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Map<String, Answer> getAnswers() {
        return Answers;
    }

    public void setAnswers(Map<String, Answer> answers) {
        Answers = answers;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
