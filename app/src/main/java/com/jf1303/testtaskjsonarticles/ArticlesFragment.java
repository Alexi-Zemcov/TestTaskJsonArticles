package com.jf1303.testtaskjsonarticles;

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

public class ArticlesFragment extends Fragment {
    private static final String TAG = "ArticlesFragment";
    private RecyclerView mArticlesRecyclerView;
    private List<ArticleItem> mItems = new ArrayList<>();


    public static ArticlesFragment newInstance() {
        return new ArticlesFragment();
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
        View v = inflater.inflate(R.layout.fragment_articels_list, container,
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

    private class ArticleHolder extends RecyclerView.ViewHolder {
        private TextView mTitleTextView;
        public ArticleHolder(View itemView) {
            super(itemView);
            mTitleTextView = (TextView) itemView;
        }
        public void bindArticlesItem(ArticleItem item) {
            mTitleTextView.setText(item.getTitle());
        }
    }

    private class ArticleAdapter extends RecyclerView.Adapter<ArticleHolder> {
        private List<ArticleItem> mArticlesItems;

        public ArticleAdapter(List<ArticleItem> ArticlesItems) {
            mArticlesItems = ArticlesItems;
        }

        @Override
        public ArticleHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            TextView textView = new TextView(getActivity());
            return new ArticleHolder(textView);
        }

        @Override
        public void onBindViewHolder(ArticleHolder articleHolder, int position) {
            ArticleItem articleItem = mArticlesItems.get(position);
            articleHolder.bindArticlesItem(articleItem);
        }

        @Override
        public int getItemCount() {
            return mArticlesItems.size();
        }
    }


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
