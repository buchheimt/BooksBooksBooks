package com.tyler_buchheim.booksbooksbooks;

import android.content.AsyncTaskLoader;
import android.content.Context;
import java.util.List;

public class BookLoader extends AsyncTaskLoader<List<Book>>{

    private String mSearchParam;

    public BookLoader(Context context, String searchParam) {
        super(context);
        mSearchParam = searchParam;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Book> loadInBackground() {
        if (mSearchParam == null) {
            return null;
        }
        return QueryUtils.fetchBookData(mSearchParam);
    }
}
