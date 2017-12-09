package com.tyler_buchheim.booksbooksbooks;

import android.text.TextUtils;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public final class QueryUtils {

    private static final String BASE_URL_STRING = "https://www.googleapis.com/books/v1/";
    private static final String LOG_TAG = QueryUtils.class.getName();

    private QueryUtils() {}

    public static ArrayList<Book> fetchBookData(String searchParam) {
        URL url = createFullUrl(searchParam);

        String jsonResponse = "";
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "The HTTP connection failed: " + e);
        }

        return extractBooks(jsonResponse);
    }

    private static URL createFullUrl(String searchParam) {
        URL url = null;

        try {
            URL baseURL = new URL(BASE_URL_STRING);
            url = new URL(baseURL, "volumes?q=" + searchParam);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "There was an error formatting url: " + e);
        }

        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Connection did not return a 200 ok response, URL: " + url.toString());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "There was an error connecting to the Google API: " + e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();

        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,
                    Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }

        return output.toString();
    }


    private static ArrayList<Book> extractBooks(String bookJSON) {

        if (bookJSON.isEmpty()) {
            return null;
        }

        ArrayList<Book> books = new ArrayList<>();

        try {
            JSONObject root = new JSONObject(bookJSON);
            JSONArray items = root.getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                JSONObject book = items.getJSONObject(i);
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");

                String title = volumeInfo.getString("title");

                String authors = null;
                try {
                    JSONArray authorList = volumeInfo.getJSONArray("authors");
                    StringBuilder authorsBuilder = new StringBuilder();
                    for (int j = 0; j < authorList.length() - 1; j++) {
                        authorsBuilder.append(authorList.getString(j));
                        authorsBuilder.append(", ");
                    }
                    authorsBuilder.append(authorList.getString(authorList.length() - 1));
                    authors = authorsBuilder.toString();
                } catch (JSONException e) {
                    Log.e(LOG_TAG, "No authors array for: " + book.toString());
                }
                if (authors == null) {
                    authors = "Unknown";
                }

                String pageCount = null;
                try {
                    pageCount = volumeInfo.getString("pageCount") + " pages";
                } catch (JSONException e) {
                    Log.e(LOG_TAG, "No pageCount for: " + book.toString());
                }
                if (pageCount == null) {
                    pageCount = "Unknown";
                }

                books.add(new Book(title, authors, pageCount));
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "There was an error during JSON parsing: " + e);
        }

        return books;
    }
}
