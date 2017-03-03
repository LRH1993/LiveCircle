package com.lvr.livecircle.meitu;

import android.content.Context;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;
import com.lvr.livecircle.R;
import com.lvr.livecircle.adapter.PhotoListAdapter;
import com.lvr.livecircle.base.BaseFragment;
import com.lvr.livecircle.bean.FabScrollBean;
import com.lvr.livecircle.client.RxDisposeManager;
import com.lvr.livecircle.meitu.model.bean.PhotoGirl;
import com.lvr.livecircle.meitu.presenter.impl.PhotoPresenterImpl;
import com.lvr.livecircle.meitu.view.PhotoView;
import com.lvr.livecircle.widget.LoadMoreFooterView;
import com.lvr.livecircle.widget.LoadingDialog;

import java.util.List;

import butterknife.BindView;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * Created by lvr on 2017/2/6.
 */

public class MeiTuFragment extends BaseFragment implements PhotoView, OnRefreshListener, OnLoadMoreListener {
    @BindView(R.id.recyclerView)
    IRecyclerView mRecyclerView;
    private Context mContext;
    private static int SIZE = 20;
    private int mStartPage = 1;
    private PhotoPresenterImpl mPresenter;
    private PhotoListAdapter mAdapter;
    private LoadMoreFooterView mFooterView;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_meitu;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        if(isVisible){
            //可见，并且是第一次加载
            mPresenter.requestPhotoList(SIZE,mStartPage);
            LoadingDialog.showDialogForLoading(getActivity());
        }else{
            //取消加载
            RxDisposeManager.get().cancel("photoList");
            LoadingDialog.cancelDialogForLoading();
        }
    }

    @Override
    protected void initView() {

        mContext = getActivity();
        mPresenter = new PhotoPresenterImpl(this);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        manager.setItemPrefetchEnabled(false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setOnRefreshListener(this);
        mRecyclerView.setOnLoadMoreListener(this);
        mFooterView = (LoadMoreFooterView) mRecyclerView.getLoadMoreFooterView();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Subscribe
    public void onFabScrollEvent(FabScrollBean event) {
        if(event.getPosition()==3){
            mRecyclerView.smoothScrollToPosition(0);
        }
    }

    @Override
    public void returnPhotoList(List<PhotoGirl> photoGirlList) {
        if (!isFirst) {
            isFirst = true;
            LoadingDialog.cancelDialogForLoading();
            mAdapter = new PhotoListAdapter(mContext, photoGirlList);
            mRecyclerView.setIAdapter(mAdapter);
        } else if (photoGirlList != null) {
            if (mStartPage == 1) {
                //刷新请求
                mRecyclerView.setRefreshing(false);
                List<PhotoGirl> data = mAdapter.getAdapterData();
                data.clear();
                data.addAll(photoGirlList);
                mAdapter.notifyDataSetChanged();
            } else {
                //加载更多
                List<PhotoGirl> data = mAdapter.getAdapterData();
                data.addAll(photoGirlList);
                mAdapter.notifyDataSetChanged();
            }
        } else {
            mStartPage--;
            mFooterView.setStatus(LoadMoreFooterView.Status.THE_END);
        }
    }

    @Override
    public void showErrorTip() {
        if (mFooterView != null) {
            mFooterView.setStatus(LoadMoreFooterView.Status.ERROR);
        }
    }

    @Override
    public void onLoadMore() {
        mStartPage++;
        mPresenter.requestPhotoList(SIZE, mStartPage);
        mFooterView.setStatus(LoadMoreFooterView.Status.LOADING);
    }

    @Override
    public void onRefresh() {
        //发起请求
        mRecyclerView.setRefreshing(true);
        mStartPage = 1;
        mPresenter.requestPhotoList(SIZE, mStartPage);
        mFooterView.setStatus(LoadMoreFooterView.Status.GONE);
    }




}
