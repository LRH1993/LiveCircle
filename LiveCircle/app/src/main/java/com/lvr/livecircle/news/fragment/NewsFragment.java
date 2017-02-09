package com.lvr.livecircle.news.fragment;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lvr.livecircle.R;
import com.lvr.livecircle.adapter.NewsChannelGridAdapter;
import com.lvr.livecircle.adapter.NewsListAdapter;
import com.lvr.livecircle.base.BaseFragment;
import com.lvr.livecircle.bean.NewsInfo;
import com.lvr.livecircle.news.model.bean.NewsChannelTable;
import com.lvr.livecircle.news.presenter.impl.NewsPresenterImpl;
import com.lvr.livecircle.news.view.NewsView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by lvr on 2017/2/6.
 */

public class NewsFragment extends BaseFragment implements NewsView {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout mRefreshLayout;
    private NewsPresenterImpl mPresenter;
    private NewsChannelGridAdapter mAdapter;
    private Context mContext;
    private List<NewsChannelTable> mTables;
    private List<NewsInfo> mInfos;
    private NewsListAdapter mNewsListAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_news;
    }


    @Override
    protected void initView() {
        mContext = getActivity();
        mPresenter = new NewsPresenterImpl(this);
        mPresenter.lodeMineChannelsRequest();
        mPresenter.loadNewsListRequest("headline", "T1348647909107", 0);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    @Override
    public void returnNewsChannel(List<NewsChannelTable> tables) {
        System.out.println(tables);
        mTables = tables;
        addAllChannel();
    }

    @Override
    public void returnNewsList(List<NewsInfo> infos) {
        mInfos = infos;
        mNewsListAdapter = new NewsListAdapter(mContext, mInfos,mTables);
        mRecyclerView.setAdapter(mNewsListAdapter);
    }

    /**
     * 添加跳转到管理新闻频道的界面
     */
    private void addAllChannel() {
        if (mTables != null) {
            NewsChannelTable allChannel = new NewsChannelTable("全部", "", "", false, 0, true, R.drawable.news_all);
            mTables.add(allChannel);
        }
    }
}
