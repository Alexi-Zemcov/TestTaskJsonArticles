package com.jf1303.testtaskjsonarticles;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import java.util.UUID;

public class ArticleActivity extends SingleFragmentActivity{
    public static final String EXTRA_ARTICLE_ID = "com.jf1303.testtaskjsonarticles.article_id";
    public static final String EXTRA_USER_ID = "com.jf1303.testtaskjsonarticles.user_id";
    public static final String EXTRA_ARTICLE_TITLE = "com.jf1303.testtaskjsonarticles.article_title";
    public static final String EXTRA_ARTICLE_BODY = "com.jf1303.testtaskjsonarticles.article_body";

    public static Intent newIntent(Context packageContext,
                                   String articleId,
                                   String userId,
                                   String articleTitle,
                                   String articleBody){
        Intent intent = new Intent(packageContext, ArticleActivity.class);
        intent.putExtra(EXTRA_ARTICLE_ID, articleId);
        intent.putExtra(EXTRA_USER_ID, userId);
        intent.putExtra(EXTRA_ARTICLE_TITLE, articleTitle);
        intent.putExtra(EXTRA_ARTICLE_BODY, articleBody);
        return intent;
    }


    @Override
    protected Fragment createFragment() {
        return ArticleFragment.newInstance();
    }
}


//public class ArticlesListActivity extends SingleFragmentActivity{
//
//    @Override
//    protected Fragment createFragment() {
//        return ArticlesListFragment.newInstance();
//    }
//}
