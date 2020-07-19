package com.jf1303.testtaskjsonarticles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ArticleFragment extends Fragment{
    private ArticleItem mArticleItem;

    private TextView mTitleTextView;
    private TextView mBodyTextView;
    private TextView mUserIdTextView;
    private TextView mArticleIdTextView;

    public static Fragment newInstance() {
        return new ArticleFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArticleItem = new ArticleItem();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_article, container, false);

        mTitleTextView = (TextView) v.findViewById(R.id.articleTitle);
//        mTitleTextView.setText("test " + mArticleItem.getTitle());
        mTitleTextView.setText("articleTitle");

        mBodyTextView = (TextView) v.findViewById(R.id.articleBody);
//        mBodyTextView.setText("test " + mArticleItem.getBody());
        mBodyTextView.setText("articleBody");

        mUserIdTextView = (TextView) v.findViewById(R.id.userID);
//        mUserIdTextView.setText("test " + mArticleItem.getUserId());
        mUserIdTextView.setText("userID");

        mArticleIdTextView = (TextView) v.findViewById(R.id.articleID);
//        mArticleIdTextView.setText("test " + mArticleItem.getTitle());
        mArticleIdTextView.setText("articleID");



        return v;
    }
}
