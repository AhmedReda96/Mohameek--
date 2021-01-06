package mfl.com.pojo.location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cities {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("governorate_id")
    @Expose
    private Integer governorateId;
    @SerializedName("city_name")
    @Expose
    private String cityName;
    @SerializedName("city_name_en")
    @Expose
    private String cityNameEn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGovernorateId() {
        return governorateId;
    }

    public void setGovernorateId(Integer governorateId) {
        this.governorateId = governorateId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityNameEn() {
        return cityNameEn;
    }

    public void setCityNameEn(String cityNameEn) {
        this.cityNameEn = cityNameEn;
    }

}
