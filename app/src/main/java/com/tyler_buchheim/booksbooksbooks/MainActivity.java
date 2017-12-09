package com.tyler_buchheim.booksbooksbooks;

import android.content.Context;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.ProgressBar;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<Book>> {

    private BookAdapter mAdapter;
    private String mSearchParam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView bookListView = (ListView) findViewById(R.id.list);
        final TextView emptyView = (TextView) findViewById(R.id.empty);
        mAdapter = new BookAdapter(this, new ArrayList<Book>());

        // Save state of scroll to preserve position on rotation
        Parcelable state = bookListView.onSaveInstanceState();

        bookListView.setAdapter(mAdapter);
        bookListView.setEmptyView(findViewById(R.id.empty));

        // Initialize loader if there is a connection or set empty view text
        final ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo initialNetworkInfo = connMgr.getActiveNetworkInfo();
        if (initialNetworkInfo != null && initialNetworkInfo.isConnected()) {
            getLoaderManager().initLoader(0, null, this);
        } else {
            mAdapter.clear();
            emptyView.setText(R.string.no_connection);
        }

        // Restore scroll state on rotation
        bookListView.onRestoreInstanceState(state);

        final EditText searchTextView = (EditText) findViewById(R.id.search_field);
        Button searchButton = (Button) findViewById(R.id.search_button);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                mSearchParam = searchTextView.getText().toString();

                // Restart loader with new search if connected or clear adapter and set empty text
                if (networkInfo != null && networkInfo.isConnected()) {
                    getLoaderManager().restartLoader(0, null, MainActivity.this);
                    findViewById(R.id.progress_bar).setVisibility(View.VISIBLE);
                } else {
                    mAdapter.clear();
                    findViewById(R.id.progress_bar).setVisibility(GONE);
                    emptyView.setText(R.string.no_connection);
                }
            }
        });
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int i, Bundle bundle) {
        return new BookLoader(this, mSearchParam);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {
        mAdapter.clear();
        TextView emptyView = (TextView) findViewById(R.id.empty);

        if (books == null) {
            emptyView.setText(R.string.no_search_term);
        } else if (books.isEmpty()) {
            emptyView.setText(R.string.no_results);
        } else {
            mAdapter.addAll(books);
        }

        findViewById(R.id.progress_bar).setVisibility(GONE);
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        mAdapter.clear();
    }
}
