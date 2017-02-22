package com.lvr.livecircle.news.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.lvr.livecircle.R;
import com.lvr.livecircle.adapter.NewsChannelAdapter;
import com.lvr.livecircle.base.BaseActivity;
import com.lvr.livecircle.bean.ChannelBean;
import com.lvr.livecircle.news.model.bean.NewsChannelTable;
import com.lvr.livecircle.news.presenter.NewsChannelPresenter;
import com.lvr.livecircle.news.presenter.impl.NewsChannelPresenterImpl;
import com.lvr.livecircle.news.view.NewsChannelView;
import com.lvr.livecircle.utils.ToastUitl;
import com.lvr.livecircle.widget.ItemDragHelperCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * 新闻频道管理页面
 * Created by lvr on 2017/2/11.
 */

public class NewsChannelActivity extends BaseActivity implements NewsChannelView {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.news_channel_mine_rv)
    RecyclerView mNewsChannelMineRv;
    @BindView(R.id.news_channel_more_rv)
    RecyclerView mNewsChannelMoreRv;
    private NewsChannelPresenter mPresenter;
    private Context mContext;
    private NewsChannelAdapter mMineAdapter;
    private NewsChannelAdapter mMoreAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_news_channel;
    }

    @Override
    public void initPresenter() {
        mPresenter = new NewsChannelPresenterImpl(this);
    }

    @Override
    public void initView() {
        SetStatusBarColor();
        mContext = this;
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAfterTransition();
                } else {
                    finish();
                }
            }
        });
        mPresenter.lodeChannelsRequest();
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }
    @Subscribe
    public void onChannelEvent(ChannelBean event){
        List<NewsChannelTable> list = event.getList();
        System.out.println("接收移动的消息");
        mPresenter.onItemSwap((ArrayList<NewsChannelTable>) list);
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


    @Override
    public void returnMineNewsChannels(List<NewsChannelTable> newsChannelsMine) {
        mMineAdapter = new NewsChannelAdapter(mContext, newsChannelsMine);
        mNewsChannelMineRv.setLayoutManager(new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false));
        mNewsChannelMineRv.setItemAnimator(new DefaultItemAnimator());
        mNewsChannelMineRv.setAdapter(mMineAdapter);
        //加载数据完毕后 设置监听，拖拽移动事件 和 点击事件  把ItemDragHelperCallback设置给RecyclerView使用
        ItemDragHelperCallback callback = new ItemDragHelperCallback(mMineAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(mNewsChannelMineRv);
        mMineAdapter.setItemDragHelperCallback(callback);
        mMineAdapter.setOnItemClickListener(new NewsChannelAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                NewsChannelTable newsChannel = mMineAdapter.getAdapterData().get(position);
                mMoreAdapter.getAdapterData().add(newsChannel);
                mMoreAdapter.notifyDataSetChanged();
                mMineAdapter.getAdapterData().remove(position);
                mMineAdapter.notifyDataSetChanged();
                //进行添加或删除操作后，要更新的列表 进行存储
                mPresenter.onItemAddOrRemove((ArrayList<NewsChannelTable>) mMineAdapter.getAdapterData(), (ArrayList<NewsChannelTable>) mMoreAdapter.getAdapterData());

            }
        });
    }

    @Override
    public void returnMoreNewsChannels(List<NewsChannelTable> newsChannelsMore) {
        mMoreAdapter = new NewsChannelAdapter(mContext, newsChannelsMore);
        mNewsChannelMoreRv.setLayoutManager(new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false));
        mNewsChannelMoreRv.setItemAnimator(new DefaultItemAnimator());
        mNewsChannelMoreRv.setAdapter(mMoreAdapter);
        mMoreAdapter.setOnItemClickListener(new NewsChannelAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(mMineAdapter.getAdapterData().size()==7){
                    ToastUitl.showShort("最多只能添加7个");
                }else{
                NewsChannelTable newsChannel = mMoreAdapter.getAdapterData().get(position);
                mMoreAdapter.getAdapterData().remove(position);
                mMoreAdapter.notifyDataSetChanged();
                mMineAdapter.getAdapterData().add(newsChannel);
                mMineAdapter.notifyDataSetChanged();
                List<NewsChannelTable> data = mMineAdapter.getAdapterData();
                for (NewsChannelTable table : data) {
                    System.out.println(table);
                }
                //进行添加或删除操作后，要更新的列表 进行存储
                mPresenter.onItemAddOrRemove((ArrayList<NewsChannelTable>) mMineAdapter.getAdapterData(), (ArrayList<NewsChannelTable>) mMoreAdapter.getAdapterData());
            }


            }
        });
    }
}
