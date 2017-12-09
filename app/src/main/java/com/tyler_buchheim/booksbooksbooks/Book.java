package com.tyler_buchheim.booksbooksbooks;

public class Book {

    private String mTitle;
    private String mAuthor;
    private String mPageCount;

    public Book(String title, String author, String pageCount) {
        mTitle = title;
        mAuthor = author;
        mPageCount = pageCount;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getPageCount() {
        return mPageCount;
    }
}
