package com.lvr.livecircle.news.fragment;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.lvr.livecircle.R;
import com.lvr.livecircle.adapter.NewsChannelGridAdapter;
import com.lvr.livecircle.base.BaseFragment;
import com.lvr.livecircle.news.model.bean.NewsChannelTable;
import com.lvr.livecircle.news.presenter.impl.NewsPresenterImpl;
import com.lvr.livecircle.news.view.NewsView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by lvr on 2017/2/6.
 */

public class NewsFragment extends BaseFragment implements NewsView {
    @BindView(R.id.gv_news_channel)
    GridView mGvNewsChannel;
    private NewsPresenterImpl mPresenter;
    private NewsChannelGridAdapter mAdapter;
    private Context mContext;
    private List<NewsChannelTable> mTables;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_news;
    }


    @Override
    protected void initView() {
        mContext = getActivity();
        mPresenter = new NewsPresenterImpl(this);
        mPresenter.lodeMineChannelsRequest();

    }

    @Override
    public void returnNewsChannel(List<NewsChannelTable> tables) {
        System.out.println(tables);
        mTables = tables;
        addAllChannel();
        mAdapter = new NewsChannelGridAdapter(mTables, mContext);
        mGvNewsChannel.setAdapter(mAdapter);
        mGvNewsChannel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String name = mTables.get(position).getNewsChannelName();
                showShortToast(name);
            }
        });
    }

    /**
     * 添加跳转到管理新闻频道的界面
     */
    private void addAllChannel() {
        if(mTables!=null){
            NewsChannelTable allChannel = new NewsChannelTable("全部", "", "", false, 0, true, R.drawable.news_all);
            mTables.add(allChannel);
        }
    }

}
