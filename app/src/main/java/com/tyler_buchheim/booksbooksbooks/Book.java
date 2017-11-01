package com.tyler_buchheim.booksbooksbooks;

/**
 * Created by buchh on 10/30/2017.
 */

public class Book {

    private String mTitle;
    private String mAuthor;
    private String mUrl;
    private String mPageCount;

    public Book(String title, String author, String url, String pageCount) {
        mTitle = title;
        mAuthor = author;
        mUrl = url;
        mPageCount = pageCount;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getPageCount() {
        return mPageCount;
    }
}
