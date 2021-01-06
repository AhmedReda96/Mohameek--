package mfl.com.pojo.location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CitesResponse {
    @SerializedName("message")
    @Expose
    private List<Cities> cities = null;
    @SerializedName("status")
    @Expose
    private Boolean status;

    public List<Cities> getCities() {
        return cities;
    }

    public void setCities(List<Cities> cities) {
        this.cities = cities;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
