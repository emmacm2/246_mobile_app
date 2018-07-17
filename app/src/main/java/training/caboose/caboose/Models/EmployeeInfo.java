package training.caboose.caboose.Models;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.Exclude;


@IgnoreExtraProperties
public class EmployeeInfo {
    private String email;
    private String orgId;
    private String authToken;
    private String name;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
