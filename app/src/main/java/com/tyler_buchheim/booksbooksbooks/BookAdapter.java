package com.tyler_buchheim.booksbooksbooks;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by buchh on 10/29/2017.
 */

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Activity context, ArrayList<Book> books) {
        super(context, 0, books);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,
                    parent, false);
        }

        Book currentBook = getItem(position);

        TextView titleView = listItemView.findViewById(R.id.title);
        titleView.setText(currentBook.getTitle());

        TextView authorView = listItemView.findViewById(R.id.author);
        authorView.setText(currentBook.getAuthor());

        TextView pageCountView = listItemView.findViewById(R.id.page_count);
        pageCountView.setText(currentBook.getPageCount());

        return listItemView;
    }
}
