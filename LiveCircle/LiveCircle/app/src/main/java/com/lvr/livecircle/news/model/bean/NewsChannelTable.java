package com.lvr.livecircle.news.model.bean;


import java.io.Serializable;

public class NewsChannelTable implements Serializable {

    private String newsChannelName;
    private String newsChannelId;
    private String newsChannelType;
    private boolean newsChannelSelect;
    private int newsChannelIndex;
    private Boolean newsChannelFixed;
    private int ImgRes;

    public NewsChannelTable() {
    }
    public NewsChannelTable(String newsChannelName) {
        this.newsChannelName = newsChannelName;
    }

    public NewsChannelTable(String newsChannelName, String newsChannelId, String newsChannelType, boolean newsChannelSelect, int newsChannelIndex, Boolean newsChannelFixed,int imgRes) {
        this.ImgRes = imgRes;
        this.newsChannelName = newsChannelName;
        this.newsChannelId = newsChannelId;
        this.newsChannelType = newsChannelType;
        this.newsChannelSelect = newsChannelSelect;
        this.newsChannelIndex = newsChannelIndex;
        this.newsChannelFixed = newsChannelFixed;
    }
    public boolean isNewsChannelSelect() {
        return newsChannelSelect;
    }

    public void setImgRes(int imgRes) {
        ImgRes = imgRes;
    }

    public int getImgRes() {
        return ImgRes;
    }

    public String getNewsChannelName() {
        return newsChannelName;
    }

    public void setNewsChannelName(String newsChannelName) {
        this.newsChannelName = newsChannelName;
    }

    public String getNewsChannelId() {
        return newsChannelId;
    }

    public void setNewsChannelId(String newsChannelId) {
        this.newsChannelId = newsChannelId;
    }

    public String getNewsChannelType() {
        return newsChannelType;
    }

    public void setNewsChannelType(String newsChannelType) {
        this.newsChannelType = newsChannelType;
    }

    public boolean getNewsChannelSelect() {
        return newsChannelSelect;
    }

    public void setNewsChannelSelect(boolean newsChannelSelect) {
        this.newsChannelSelect = newsChannelSelect;
    }

    public int getNewsChannelIndex() {
        return newsChannelIndex;
    }

    public void setNewsChannelIndex(int newsChannelIndex) {
        this.newsChannelIndex = newsChannelIndex;
    }

    public Boolean getNewsChannelFixed() {
        return newsChannelFixed;
    }

    public void setNewsChannelFixed(Boolean newsChannelFixed) {
        this.newsChannelFixed = newsChannelFixed;
    }

    @Override
    public String toString() {
        return "NewsChannelTable{" +
                "newsChannelName='" + newsChannelName + '\'' +
                ", newsChannelId='" + newsChannelId + '\'' +
                ", newsChannelType='" + newsChannelType + '\'' +
                ", newsChannelSelect=" + newsChannelSelect +
                ", newsChannelIndex=" + newsChannelIndex +
                ", newsChannelFixed=" + newsChannelFixed +
                ", ImgRes=" + ImgRes +
                '}';
    }
}
