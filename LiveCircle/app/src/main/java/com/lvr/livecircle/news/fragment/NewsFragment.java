package com.lvr.livecircle.news.fragment;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lvr.livecircle.R;
import com.lvr.livecircle.adapter.NewsListAdapter;
import com.lvr.livecircle.adapter.OnNewsChannelListener;
import com.lvr.livecircle.base.BaseFragment;
import com.lvr.livecircle.bean.FabScrollBean;
import com.lvr.livecircle.bean.HeaderBean;
import com.lvr.livecircle.bean.NewsInfo;
import com.lvr.livecircle.client.RxDisposeManager;
import com.lvr.livecircle.news.model.bean.NewsChannelTable;
import com.lvr.livecircle.news.presenter.impl.NewsPresenterImpl;
import com.lvr.livecircle.news.view.NewsView;
import com.lvr.livecircle.widget.LoadingTip;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * Created by lvr on 2017/2/6.
 */

public class NewsFragment extends BaseFragment implements NewsView, SwipeRefreshLayout.OnRefreshListener, OnNewsChannelListener {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.loadedTip)
    LoadingTip mLoadedTip;
    private NewsPresenterImpl mPresenter;
    private Context mContext;
    private List<NewsChannelTable> mTables = new ArrayList<>();
    private List<NewsInfo> mInfos = new ArrayList<>();
    private NewsListAdapter mNewsListAdapter;
    //当前加载新闻类型
    private String cur_news_type = "headline";
    //当前加载新闻类型的id
    private String cur_news_id = "T1348647909107";
    //当前加载新闻页数
    private int cur_news_page = 0;



    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_news;
    }


    @Override
    protected void initView() {
        mContext = getActivity();
        mPresenter = new NewsPresenterImpl(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRefreshLayout.setOnRefreshListener(this);
        //设置下拉刷新的按钮的颜色
        mRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        setLoadMoreListener();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        if (isVisible) {
            mPresenter.lodeMineChannelsRequest();
            mPresenter.loadNewsListRequest(cur_news_type, cur_news_id, cur_news_page);
        }else{
            RxDisposeManager.get().cancel("newschannel");
            RxDisposeManager.get().cancel("newslist");
        }
    }

    @Subscribe
    public void onFabScrollEvent(FabScrollBean event) {
        if(event.getPosition()==1){
            mRecyclerView.smoothScrollToPosition(0);
        }

    }

    @Subscribe
    public void onHeaderEvent(HeaderBean event) {
        returnNewsChannel(event.getList());
    }

    private void setLoadMoreListener() {
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {

            boolean isSlidingToLast = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                // 当不滚动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    // 获取最后一个完全显示的item position
                    int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                    int totalItemCount = manager.getItemCount();
                    // 判断是否滚动到底部并且是向下滑动
                    if (lastVisibleItem == (totalItemCount - 1) && isSlidingToLast) {
                        cur_news_page += 20;
                        System.out.println("加载页数：" + cur_news_page);
                        mPresenter.loadNewsListRequest(cur_news_type, cur_news_id, cur_news_page);
                    }
                }

                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                isSlidingToLast = dy > 0;

            }
        });

    }


    @Override
    public void returnNewsChannel(List<NewsChannelTable> tables) {
        System.out.println(tables);
        System.out.println("频道个数:" + tables.size());
        mTables.clear();
        mTables.addAll(tables);
        addAllChannel();
        if (mNewsListAdapter != null) {
            mNewsListAdapter.setTables(mTables);
            mNewsListAdapter.notifyItemChanged(0);
        }

    }

    @Override
    public void returnNewsList(List<NewsInfo> infos) {
        mInfos = infos;
        if (!isFirst) {
            isFirst = true;
            mNewsListAdapter = new NewsListAdapter(mContext, mInfos, mTables);
            mNewsListAdapter.setNewsChannelListener(this);
            mRecyclerView.setAdapter(mNewsListAdapter);
        } else if (cur_news_page == 0) {
            mRefreshLayout.setRefreshing(false);
            List<NewsInfo> data = mNewsListAdapter.getAdapterData();
            data.clear();
            data.addAll(infos);
            System.out.println("刷新后的数据：" + data.size());
            mNewsListAdapter.notifyDataSetChanged();
        } else if (cur_news_page > 0) {
            infos.remove(0);
            List<NewsInfo> data = mNewsListAdapter.getAdapterData();
            data.addAll(infos);
            System.out.println("加载后的数据：" + data.size());
            mNewsListAdapter.notifyDataSetChanged();
        }


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

    @Override
    public void showNetErrorTip() {
        super.showNetErrorTip();
        mRefreshLayout.setRefreshing(false);
        mLoadedTip.setLoadingTip(LoadingTip.LoadStatus.error);
    }

    public void showLoading() {
        mLoadedTip.setLoadingTip(LoadingTip.LoadStatus.loading);
    }

    public void stopLoading() {
        mLoadedTip.setLoadingTip(LoadingTip.LoadStatus.finish);
    }

    @Override
    public void onRefresh() {
        cur_news_page = 0;
        mRefreshLayout.setRefreshing(true);
        mPresenter.loadNewsListRequest(cur_news_type, cur_news_id, cur_news_page);
    }


    @Override
    public void changeChannelListener(NewsChannelTable newsChannelTable) {
        String type = newsChannelTable.getNewsChannelType();
        String id = newsChannelTable.getNewsChannelId();
        cur_news_type = type;
        cur_news_id = id;
        cur_news_page = 0;
        System.out.println(cur_news_type);
        System.out.println(cur_news_id);
        mPresenter.loadNewsListRequest(cur_news_type, cur_news_id, cur_news_page);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RxDisposeManager.get().cancel("newschannel");
        RxDisposeManager.get().cancel("newslist");
    }
}
