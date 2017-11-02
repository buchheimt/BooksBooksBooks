package com.tyler_buchheim.booksbooksbooks;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private BookAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new BookAsyncTask().execute("test");
    }

    private class BookAsyncTask extends AsyncTask<String, Void, ArrayList<Book>> {

        @Override
        protected ArrayList<Book> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            return QueryUtils.extractBooks(urls[0]);

        }

        @Override
        protected void onPostExecute(ArrayList<Book> books) {
            super.onPostExecute(books);

            ListView bookListView = (ListView) findViewById(R.id.list);
            mAdapter = new BookAdapter(MainActivity.this, books);

            bookListView.setAdapter(mAdapter);
        }
    }
}
