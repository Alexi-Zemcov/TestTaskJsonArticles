package com.jf1303.testtaskjsonarticles;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonPlaceholderFetcher {

    public byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() +
                        ": with " +
                        urlSpec);
            }
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }
    public String getUrlString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    public List<ArticleItem> fetchItems() {
        List<ArticleItem> items = new ArrayList<>();

        String TAG = "JsonPlaceholderFetcher";
        try {
            String url = "https://jsonplaceholder.typicode.com/posts";

            String jsonString = getUrlString(url);
            Log.i(TAG, "Received JSON: " + jsonString);
            JSONArray jsonBody = new JSONArray(jsonString);
            parseItems(items, jsonBody);
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items", ioe);
        } catch (JSONException je) {
            Log.e(TAG, "Failed to parse JSON", je);
        }
        return items;
    }

    private  void parseItems(List<ArticleItem> items, JSONArray jsonBody)
            throws IOException, JSONException {
        for (int i = 0; i < jsonBody.length(); i++) {
            JSONObject articlesJsonObject = jsonBody.getJSONObject(i);
            ArticleItem item = new ArticleItem();
            item.setUserId(articlesJsonObject.getString("userId"));
            item.setArticleId(articlesJsonObject.getString("id"));
            item.setTitle(articlesJsonObject.getString("title"));
            item.setBody(articlesJsonObject.getString("body"));
            items.add(item);
        }
    }
}