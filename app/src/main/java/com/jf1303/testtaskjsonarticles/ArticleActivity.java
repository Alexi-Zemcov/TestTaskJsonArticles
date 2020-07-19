package com.jf1303.testtaskjsonarticles;

import androidx.fragment.app.Fragment;

public class ArticleActivity extends SingleFragmentActivity{
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
