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

        ArrayList<Book> books = new ArrayList<Book>();
        books.add(new Book("Chamber of Secrets", "J.K. Rowling"));
        books.add(new Book("Goblet of Fire", "J.K. Rowling"));
        books.add(new Book("Half-Blood Prince", "J.K. Rowling"));
        books.add(new Book("A Game of Thrones", "George R.R. Martin"));
        books.add(new Book("A Clash of Kings", "George R.R. Martin"));
        books.add(new Book("A Storm of Swords", "George R.R. Martin"));

        ListView bookListView = (ListView) findViewById(R.id.list);
        mAdapter = new BookAdapter(this, books);

        bookListView.setAdapter(mAdapter);
    }
}
