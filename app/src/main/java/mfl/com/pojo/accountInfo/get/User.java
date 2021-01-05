package mfl.com.pojo.accountInfo.get;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("card_number")
    @Expose
    private Integer cardNumber;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("vice_call_price")
    @Expose
    private Object viceCallPrice;
    @SerializedName("bank_account_number")
    @Expose
    private Object bankAccountNumber;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("visits")
    @Expose
    private Integer visits;
    @SerializedName("step")
    @Expose
    private Integer step;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("birthdate")
    @Expose
    private String birthdate;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("lat")
    @Expose
    private Object lat;
    @SerializedName("long")
    @Expose
    private Object _long;
    @SerializedName("location")
    @Expose
    private Object location;
    @SerializedName("city_name")
    @Expose
    private Object cityName;
    @SerializedName("governorate_name")
    @Expose
    private Object governorateName;
    @SerializedName("city_name_en")
    @Expose
    private Object cityNameEn;
    @SerializedName("governorate_name_en")
    @Expose
    private Object governorateNameEn;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Integer cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Object getViceCallPrice() {
        return viceCallPrice;
    }

    public void setViceCallPrice(Object viceCallPrice) {
        this.viceCallPrice = viceCallPrice;
    }

    public Object getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(Object bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getVisits() {
        return visits;
    }

    public void setVisits(Integer visits) {
        this.visits = visits;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getLat() {
        return lat;
    }

    public void setLat(Object lat) {
        this.lat = lat;
    }

    public Object getLong() {
        return _long;
    }

    public void setLong(Object _long) {
        this._long = _long;
    }

    public Object getLocation() {
        return location;
    }

    public void setLocation(Object location) {
        this.location = location;
    }

    public Object getCityName() {
        return cityName;
    }

    public void setCityName(Object cityName) {
        this.cityName = cityName;
    }

    public Object getGovernorateName() {
        return governorateName;
    }

    public void setGovernorateName(Object governorateName) {
        this.governorateName = governorateName;
    }

    public Object getCityNameEn() {
        return cityNameEn;
    }

    public void setCityNameEn(Object cityNameEn) {
        this.cityNameEn = cityNameEn;
    }

    public Object getGovernorateNameEn() {
        return governorateNameEn;
    }

    public void setGovernorateNameEn(Object governorateNameEn) {
        this.governorateNameEn = governorateNameEn;
    }

}