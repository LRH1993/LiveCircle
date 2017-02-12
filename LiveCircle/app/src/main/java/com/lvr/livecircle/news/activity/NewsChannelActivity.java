package com.lvr.livecircle.news.activity;

import android.app.Activity;
import android.content.Intent;

import com.lvr.livecircle.R;
import com.lvr.livecircle.base.BaseActivity;

/**
 * 新闻频道管理页面
 * Created by lvr on 2017/2/11.
 */

public class NewsChannelActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_news_channel;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        SetStatusBarColor();

    }
    /**
     * 入口
     *
     * @param activity
     */
    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, NewsChannelActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);
    }
}
