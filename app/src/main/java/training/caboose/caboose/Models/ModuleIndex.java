package training.caboose.caboose.Models;

import com.google.firebase.database.IgnoreExtraProperties;




@IgnoreExtraProperties
public class ModuleIndex {
    private String uid;
    private String id;
    private int index;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public ModuleIndex(String uid, String id, int index){
        this.uid = uid;
        this.id = id;
        this.index = index;
    }

   public ModuleIndex(){}

}
