package mfl.com.pojo.workTimes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class WorkTimesModel {

    private int day;
    private String startAt;
    private String endAt;
    private int duration;

    public WorkTimesModel(int day, String startAt, String endAt, int duration) {
        this.day = day;
        this.startAt = startAt;
        this.endAt = endAt;
        this.duration = duration;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getStartAt() {
        return startAt;
    }

    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }

    public String getEndAt() {
        return endAt;
    }

    public void setEndAt(String endAt) {
        this.endAt = endAt;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
