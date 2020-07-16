package com.jf1303.testtaskjsonarticles;

public class ArticleItem {
    private String mUserId;
    private String mTitle;
    private String mBody;
    private String mArticleId;

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String mUserId) {
        this.mUserId = mUserId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getBody() {
        return mBody;
    }

    public void setBody(String mBody) {
        this.mBody = mBody;
    }

    public String getArticleId() {
        return mArticleId;
    }

    public void setArticleId(String mId) {
        this.mArticleId = mId;
    }
}
