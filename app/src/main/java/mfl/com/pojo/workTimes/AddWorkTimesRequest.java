package mfl.com.pojo.workTimes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddWorkTimesRequest {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("times")
    @Expose
    private List<WorkTimesRequest> times = null;

    public AddWorkTimesRequest(String token, List<WorkTimesRequest> times) {
        this.token = token;
        this.times = times;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<WorkTimesRequest> getTimes() {
        return times;
    }

    public void setTimes(List<WorkTimesRequest> times) {
        this.times = times;
    }

}
