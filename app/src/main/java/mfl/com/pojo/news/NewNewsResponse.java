package mfl.com.pojo.news;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewNewsResponse {


    @SerializedName("message")
    @Expose
    private NewNewsMessage message;
    @SerializedName("status")
    @Expose
    private Boolean status;

    public NewNewsMessage getMessage() {
        return message;
    }

    public void setMessage(NewNewsMessage message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
