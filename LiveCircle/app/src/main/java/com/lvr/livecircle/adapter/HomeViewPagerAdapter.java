package com.lvr.livecircle.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.lvr.livecircle.base.BaseFragmentAdapter;

import java.util.List;

/**
 * Created by lvr on 2017/2/6.
 */

public class HomeViewPagerAdapter extends BaseFragmentAdapter {
    public HomeViewPagerAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> mTitles) {
        super(fm, fragmentList, mTitles);
    }

    public HomeViewPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm, fragmentList);
    }
}
