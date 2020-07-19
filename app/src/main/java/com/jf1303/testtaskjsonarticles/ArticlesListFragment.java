package com.jf1303.testtaskjsonarticles;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ArticlesListFragment extends Fragment {
    private static final String TAG = "ArticlesFragment";
    private RecyclerView mArticlesRecyclerView;
    private List<ArticleItem> mItems = new ArrayList<>();


    public static ArticlesListFragment newInstance() {
        return new ArticlesListFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        new FetchItemsTask().execute();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_articles_list, container,
                false);
        mArticlesRecyclerView = (RecyclerView) v.findViewById(R.id.articles_recycler_view);
        mArticlesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setupAdapter();
        return v;
    }

    private void setupAdapter() {
        if (isAdded()) {
            mArticlesRecyclerView.setAdapter(new ArticleAdapter(mItems));
            Log.i(TAG, "mItems.size() in setupAdapter()" + mItems.size());
        }
    }


    private class ArticleHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{
        private ArticleItem mArticleItem;

        private TextView mTitleTextView;
        private TextView mUserIdTextView;
        private TextView mArticleIdTextView;

        public ArticleHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_article, parent, false));
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.articleTitle);
            mUserIdTextView = (TextView) itemView.findViewById(R.id.userID);
            mArticleIdTextView = (TextView) itemView.findViewById(R.id.articleID);
        }

        public void bind(ArticleItem articleItem) {
            mArticleItem = articleItem;
            mTitleTextView.setText(mArticleItem.getTitle());
            mUserIdTextView.setText(mArticleItem.getUserId());
            mArticleIdTextView.setText(mArticleItem.getArticleId());
        }

        @Override
        public void onClick(View view) {
            Intent intent = ArticleActivity.newIntent(getActivity(),
                    mArticleItem.getArticleId(),
                    mArticleItem.getUserId(),
                    mArticleItem.getTitle(),
                    mArticleItem.getBody());
            startActivity(intent);
        }
    }


    private class ArticleAdapter extends RecyclerView.Adapter<ArticleHolder> {
        private List<ArticleItem> mArticlesItems;

        public ArticleAdapter(List<ArticleItem> ArticlesItems) {
            mArticlesItems = ArticlesItems;
        }

        @NonNull
        @Override
        public ArticleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new ArticleHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(ArticleHolder articleHolder, int position) {
            ArticleItem articleItem = mArticlesItems.get(position);
            articleHolder.bind(articleItem);
        }

        @Override
        public int getItemCount() {
            return mArticlesItems.size();
        }
    }


    @SuppressLint("StaticFieldLeak")
    private class FetchItemsTask extends AsyncTask<Void,Void,List<ArticleItem>> {

        @Override
        protected List<ArticleItem> doInBackground(Void... params) {
            return new JsonPlaceholderFetcher().fetchItems();
        }

        @Override
        protected void onPostExecute(List<ArticleItem> items) {
            mItems = items;
            Log.i(TAG, "mItems.size() in onPostExecute()" + mItems.size());
            setupAdapter();
        }
    }

}
