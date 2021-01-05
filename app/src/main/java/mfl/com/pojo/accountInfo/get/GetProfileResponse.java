package mfl.com.pojo.accountInfo.get;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetProfileResponse {
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("specialties")
    @Expose
    private List<Specialty> specialties = null;
    @SerializedName("work times")
    @Expose
    private List<Object> workTimes = null;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Boolean status;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(List<Specialty> specialties) {
        this.specialties = specialties;
    }

    public List<Object> getWorkTimes() {
        return workTimes;
    }

    public void setWorkTimes(List<Object> workTimes) {
        this.workTimes = workTimes;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
