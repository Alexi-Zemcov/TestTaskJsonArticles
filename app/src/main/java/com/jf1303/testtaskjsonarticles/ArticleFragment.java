package com.jf1303.testtaskjsonarticles;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.util.Objects;

public class ArticleFragment extends Fragment{

    private Bundle mArticleBundle;

    public static Fragment newInstance() {
        return new ArticleFragment();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Пока так, потом переделаю :)
        mArticleBundle = Objects.requireNonNull(getActivity()).getIntent().getExtras();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_article, container, false);

        TextView mTitleTextView = (TextView) v.findViewById(R.id.articleTitle);
        mTitleTextView.setText(mArticleBundle.getString(ArticleActivity.EXTRA_ARTICLE_TITLE));

        TextView mBodyTextView = (TextView) v.findViewById(R.id.articleBody);
        mBodyTextView.setText(mArticleBundle.getString(ArticleActivity.EXTRA_ARTICLE_BODY));

        TextView mUserIdTextView = (TextView) v.findViewById(R.id.userID);
        mUserIdTextView.setText(mArticleBundle.getString(ArticleActivity.EXTRA_USER_ID));

        TextView mArticleIdTextView = (TextView) v.findViewById(R.id.articleID);
        mArticleIdTextView.setText(mArticleBundle.getString(ArticleActivity.EXTRA_ARTICLE_ID));



        return v;
    }
}
