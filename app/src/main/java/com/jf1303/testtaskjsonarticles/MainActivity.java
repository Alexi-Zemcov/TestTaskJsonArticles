package com.jf1303.testtaskjsonarticles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TextView mTextViewUserId;
    private TextView mTextViewId;
    private TextView mTextViewTitle;
    private TextView mTextViewBody;

    private List<ArticlesItem> mItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new FetchItemsTask().execute();

        mTextViewUserId = (TextView) findViewById(R.id.user_id);
        mTextViewUserId.setText("mItems.get(0).getUserId()");

        mTextViewId = (TextView) findViewById(R.id.article_id);
        mTextViewId.setText("mItems.get(0).getArticleId()");

        mTextViewTitle = (TextView) findViewById(R.id.title);

        mTextViewBody = (TextView) findViewById(R.id.body);

    }


    private class FetchItemsTask extends AsyncTask<Void,Void,List<ArticlesItem>> {

        @Override
        protected List<ArticlesItem> doInBackground(Void... params) {
            return new JsonPlaceholderFetcher().fetchItems();
        }

        @Override
        protected void onPostExecute(List<ArticlesItem> items) {
            mItems = items;
            Log.e(TAG, "Collection is:" + mItems.toString());
        }
    }
}