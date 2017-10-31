package com.tyler_buchheim.booksbooksbooks;

/**
 * Created by buchh on 10/30/2017.
 */

public class Book {

    private String mTitle;
    private String mAuthor;

    public Book(String title, String author) {
        mTitle = title;
        mAuthor = author;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getAuthor() {
        return mAuthor;
    }
}
