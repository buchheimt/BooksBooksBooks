package com.tyler_buchheim.booksbooksbooks;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by buchh on 10/30/2017.
 */

public final class QueryUtils {

    private static final String SAMPLE_JSON_RESPONSE = "{\n" +
            "kind: \"books#volumes\",\n" +
            "totalItems: 1500,\n" +
            "items: [\n" +
            "{\n" +
            "kind: \"books#volume\",\n" +
            "id: \"szF_pLGmJTQC\",\n" +
            "etag: \"eQi5Q3G4KY0\",\n" +
            "selfLink: \"https://www.googleapis.com/books/v1/volumes/szF_pLGmJTQC\",\n" +
            "volumeInfo: {\n" +
            "title: \"Baptizing Harry Potter\",\n" +
            "subtitle: \"A Christian Reading of J.K. Rowling\",\n" +
            "authors: [\n" +
            "\"Luke Bell\"\n" +
            "],\n" +
            "publisher: \"Paulist Press\",\n" +
            "publishedDate: \"2010\",\n" +
            "description: \"The scope and tragedy of the thing : the structure of the series -- More things in heaven and earth : going beyond the normal -- As if a man were author of himself : good against evil -- Be absolute for death : life and death -- Power is made perfect in weakness : power and weakness -- To lay down one's life for one's friends : love and sacrifice -- It is essential that you understand this : freedom and determination -- A pretty boring life : the hidden and the ostentatious -- Telling it like it is : the struggle for truth -- They will see God : purity of heart and purity of blood.\",\n" +
            "industryIdentifiers: [\n" +
            "{\n" +
            "type: \"ISBN_13\",\n" +
            "identifier: \"9781616431150\"\n" +
            "},\n" +
            "{\n" +
            "type: \"ISBN_10\",\n" +
            "identifier: \"1616431156\"\n" +
            "}\n" +
            "],\n" +
            "readingModes: {\n" +
            "text: false,\n" +
            "image: true\n" +
            "},\n" +
            "pageCount: 224,\n" +
            "printType: \"BOOK\",\n" +
            "categories: [\n" +
            "\"Children\"\n" +
            "],\n" +
            "averageRating: 3,\n" +
            "ratingsCount: 2,\n" +
            "maturityRating: \"NOT_MATURE\",\n" +
            "allowAnonLogging: false,\n" +
            "contentVersion: \"0.1.0.0.preview.1\",\n" +
            "panelizationSummary: {\n" +
            "containsEpubBubbles: false,\n" +
            "containsImageBubbles: false\n" +
            "},\n" +
            "imageLinks: {\n" +
            "smallThumbnail: \"http://books.google.com/books/content?id=szF_pLGmJTQC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
            "thumbnail: \"http://books.google.com/books/content?id=szF_pLGmJTQC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
            "},\n" +
            "language: \"en\",\n" +
            "previewLink: \"http://books.google.com/books?id=szF_pLGmJTQC&printsec=frontcover&dq=potter&hl=&cd=1&source=gbs_api\",\n" +
            "infoLink: \"https://play.google.com/store/books/details?id=szF_pLGmJTQC&source=gbs_api\",\n" +
            "canonicalVolumeLink: \"https://market.android.com/details?id=book-szF_pLGmJTQC\"\n" +
            "},\n" +
            "saleInfo: {\n" +
            "country: \"US\",\n" +
            "saleability: \"FOR_SALE\",\n" +
            "isEbook: true,\n" +
            "listPrice: {\n" +
            "amount: 12.95,\n" +
            "currencyCode: \"USD\"\n" +
            "},\n" +
            "retailPrice: {\n" +
            "amount: 9.99,\n" +
            "currencyCode: \"USD\"\n" +
            "},\n" +
            "buyLink: \"https://play.google.com/store/books/details?id=szF_pLGmJTQC&rdid=book-szF_pLGmJTQC&rdot=1&source=gbs_api\",\n" +
            "offers: [\n" +
            "{\n" +
            "finskyOfferType: 1,\n" +
            "listPrice: {\n" +
            "amountInMicros: 12950000,\n" +
            "currencyCode: \"USD\"\n" +
            "},\n" +
            "retailPrice: {\n" +
            "amountInMicros: 9990000,\n" +
            "currencyCode: \"USD\"\n" +
            "},\n" +
            "giftable: true\n" +
            "}\n" +
            "]\n" +
            "},\n" +
            "accessInfo: {\n" +
            "country: \"US\",\n" +
            "viewability: \"PARTIAL\",\n" +
            "embeddable: true,\n" +
            "publicDomain: false,\n" +
            "textToSpeechPermission: \"ALLOWED\",\n" +
            "epub: {\n" +
            "isAvailable: false\n" +
            "},\n" +
            "pdf: {\n" +
            "isAvailable: true,\n" +
            "acsTokenLink: \"http://books.google.com/books/download/Baptizing_Harry_Potter-sample-pdf.acsm?id=szF_pLGmJTQC&format=pdf&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
            "},\n" +
            "webReaderLink: \"http://play.google.com/books/reader?id=szF_pLGmJTQC&hl=&printsec=frontcover&source=gbs_api\",\n" +
            "accessViewStatus: \"SAMPLE\",\n" +
            "quoteSharingAllowed: false\n" +
            "},\n" +
            "searchInfo: {\n" +
            "textSnippet: \"The scope and tragedy of the thing : the structure of the series -- More things in heaven and earth : going beyond the normal -- As if a man were author of himself : good against evil -- Be absolute for death : life and death -- Power is ...\"\n" +
            "}\n" +
            "}\n" +
            "]\n" +
            "}";

    private QueryUtils() {

    }


    public static ArrayList<Book> extractBooks(String bookJSON) {
        bookJSON = SAMPLE_JSON_RESPONSE;
        if (TextUtils.isEmpty(bookJSON)) {
            return null;
        }

        ArrayList<Book> books = new ArrayList<>();

        try {
            JSONObject root = new JSONObject(SAMPLE_JSON_RESPONSE);
            JSONArray items = root.getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                JSONObject book = items.getJSONObject(i);
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");

                JSONArray authorList = volumeInfo.getJSONArray("authors");
                String authors = "";
                for (int j = 0; j < authorList.length() - 1; j++) {
                    authors += authorList.getString(j) + ", ";
                }
                authors += authorList.getString(authorList.length() - 1);

                String title = volumeInfo.getString("title");
                String url = volumeInfo.getString("infoLink");
                String pageCount = volumeInfo.getString("pageCount") + " pages";

                books.add(new Book(title, authors, url, pageCount));
            }
        } catch (JSONException e) {
            Log.e("QueryUtils", "There was an error during JSON parsing: " + e);
        }

        return books;
    }
}
