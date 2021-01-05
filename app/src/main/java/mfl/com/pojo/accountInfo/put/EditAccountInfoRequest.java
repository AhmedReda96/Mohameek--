package mfl.com.pojo.accountInfo.put;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EditAccountInfoRequest {
    @SerializedName("type_id")
    @Expose
    private Integer typeId;
    @SerializedName("specialties_id")
    @Expose
    private List<Integer> specialtiesId = null;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("birthdate")
    @Expose
    private String birthdate;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("description")
    @Expose
    private String description;


    public EditAccountInfoRequest(Integer typeId, List<Integer> specialtiesId, String photo, String phone, String birthdate, String token, String description) {
        this.typeId = typeId;
        this.specialtiesId = specialtiesId;
        this.photo = photo;
        this.phone = phone;
        this.birthdate = birthdate;
        this.token = token;
        this.description = description;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public List<Integer> getSpecialtiesId() {
        return specialtiesId;
    }

    public void setSpecialtiesId(List<Integer> specialtiesId) {
        this.specialtiesId = specialtiesId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
