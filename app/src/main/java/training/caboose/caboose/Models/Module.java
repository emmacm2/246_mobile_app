package training.caboose.caboose.Models;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Module{
    private String name;
    private String id;
    private String type;
    private String htmlData;
    private String youtubeData;
    private String uid;
    private Boolean complete;

    public Module(String name, String id, String type, String htmlData, String youtubeData, String uid) {
        this.name = name;
        this.id = id;
        this.type = type;
        this.htmlData = htmlData;
        this.youtubeData = youtubeData;
        this.uid = uid;
    }

    public Module() {    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHtmlData() {
        return htmlData;
    }

    public void setHtmlData(String htmlData) {
        this.htmlData = htmlData;
    }

    public String getYoutubeData() {
        return youtubeData;
    }

    public void setYoutubeData(String youtubeData) {
        this.youtubeData = youtubeData;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }
}
