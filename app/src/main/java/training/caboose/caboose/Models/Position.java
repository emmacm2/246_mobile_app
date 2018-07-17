package training.caboose.caboose.Models;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class Position {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Map<String, Object> getModules() {
        return modules;
    }

    public void setModules(Map<String, Object> modules) {
        this.modules = modules;
    }

    private String name;
    private String uid;
    private String assignedData;
    private Map<String, Object> modules = new HashMap<>();

    public Position(){}

    public Position(String name, String uid){
        this.name = name;
        this.uid = uid;
    }

    public String getAssignedData() {
        return assignedData;
    }

    public void setAssignedData(String assignedData) {
        this.assignedData = assignedData;
    }
}
