package mfl.com.db.news;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "newsTable")
public class NewsEntity {
    @PrimaryKey
    @NonNull
    int newsId;
    String newsTitle,newsImg,newsDescription,createdBy,newsDate;

    public NewsEntity(@NonNull int newsId, String newsTitle, String newsImg, String newsDescription, String createdBy, String newsDate) {
        this.newsId = newsId;
        this.newsTitle = newsTitle;
        this.newsImg = newsImg;
        this.newsDescription = newsDescription;
        this.createdBy = createdBy;
        this.newsDate = newsDate;
    }

    @NonNull
    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(@NonNull int newsId) {
        this.newsId = newsId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsImg() {
        return newsImg;
    }

    public void setNewsImg(String newsImg) {
        this.newsImg = newsImg;
    }

    public String getNewsDescription() {
        return newsDescription;
    }

    public void setNewsDescription(String newsDescription) {
        this.newsDescription = newsDescription;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(String newsDate) {
        this.newsDate = newsDate;
    }
}
