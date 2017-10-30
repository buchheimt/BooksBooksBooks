package com.tyler_buchheim.booksbooksbooks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private BookAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> books = new ArrayList<String>();
        books.add("dummy book");
        books.add("dummy book");
        books.add("dummy book");

        ListView bookListView = (ListView) findViewById(R.id.list);
        mAdapter = new BookAdapter(this, books);

        bookListView.setAdapter(mAdapter);
    }
}
