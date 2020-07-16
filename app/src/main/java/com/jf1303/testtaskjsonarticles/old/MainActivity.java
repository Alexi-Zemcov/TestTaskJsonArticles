package com.jf1303.testtaskjsonarticles.old;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.jf1303.testtaskjsonarticles.ArticleItem;
import com.jf1303.testtaskjsonarticles.JsonPlaceholderFetcher;
import com.jf1303.testtaskjsonarticles.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TextView mTextViewUserId;
    private TextView mTextViewId;
    private TextView mTextViewTitle;
    private TextView mTextViewBody;

    private List<ArticleItem> mItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new FetchItemsTask().execute();

        // Если обратиться к полученной коллекции здесь, то приложение не запускается
        Log.e(TAG, "Collection size from \"onCreate()\" is:" + mItems.size());
        mTextViewUserId = (TextView) findViewById(R.id.user_id);
        mTextViewId = (TextView) findViewById(R.id.article_id);
        mTextViewTitle = (TextView) findViewById(R.id.title);
        mTextViewBody = (TextView) findViewById(R.id.body);
    }




    private class FetchItemsTask extends AsyncTask<Void,Void,List<ArticleItem>> {

        @Override
        protected List<ArticleItem> doInBackground(Void... params) {
            return new JsonPlaceholderFetcher().fetchItems();
        }

        @Override
        protected void onPostExecute(List<ArticleItem> items) {
            mItems = items;
//            Обращение к коллекции здесь выдает корректный результат
            Log.e(TAG, "Collection size from \"onPostExecute()\" is: " + mItems.size());
            updateUi();
        }
    }

    private void updateUi() {
        mTextViewUserId.setText(mItems.get(0).getUserId());
        mTextViewId.setText(mItems.get(0).getArticleId());
        mTextViewTitle.setText(mItems.get(0).getTitle());
        mTextViewBody.setText(mItems.get(0).getBody());
    }
}