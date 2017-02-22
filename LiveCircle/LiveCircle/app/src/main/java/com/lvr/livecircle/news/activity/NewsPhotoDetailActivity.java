package com.lvr.livecircle.news.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lvr.livecircle.R;
import com.lvr.livecircle.adapter.CardAdapter;
import com.lvr.livecircle.app.AppConstant;
import com.lvr.livecircle.base.BaseActivity;
import com.lvr.livecircle.news.model.bean.NewsPhotoDetail;
import com.lvr.livecircle.recycleviewcardgallery.CardScaleHelper;
import com.lvr.livecircle.recycleviewcardgallery.SpeedRecyclerView;
import com.lvr.livecircle.utils.StatusBarSetting;

import butterknife.BindView;

/**
 * Created by lvr on 2017/2/11.
 */

public class NewsPhotoDetailActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.blurView)
    ImageView mBlurView;
    @BindView(R.id.recyclerView)
    SpeedRecyclerView mRecyclerView;
    @BindView(R.id.photo_detail_title_tv)
    TextView mPhotoDetailTitleTv;
    private NewsPhotoDetail mNewsPhotoDetail;
    private CardScaleHelper mCardScaleHelper = null;
    private int mLastPos = -1;
    private Runnable mBlurRunnable;
    /**
     * 入口
     *
     * @param context
     * @param mNewsPhotoDetail
     */
    public static void startAction(Context context, NewsPhotoDetail mNewsPhotoDetail) {
        Intent intent = new Intent(context, NewsPhotoDetailActivity.class);
        intent.putExtra(AppConstant.PHOTO_DETAIL, mNewsPhotoDetail);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_news_photo_detail;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        StatusBarSetting.setColor(NewsPhotoDetailActivity.this,getResources().getColor(R.color.black));
        mNewsPhotoDetail = getIntent().getParcelableExtra(AppConstant.PHOTO_DETAIL);
        System.out.println(mNewsPhotoDetail);
        mRecyclerView = (SpeedRecyclerView) findViewById(R.id.recyclerView);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(new CardAdapter(mContext,mNewsPhotoDetail.getPictures()));
        // mRecyclerView绑定scale效果
        mCardScaleHelper = new CardScaleHelper();
        mCardScaleHelper.setCurrentItemPos(0);
        mCardScaleHelper.attachToRecyclerView(mRecyclerView);
        String title = mNewsPhotoDetail.getPictures().get(0).getTitle()==null?"":mNewsPhotoDetail.getPictures().get(0).getTitle();
        mPhotoDetailTitleTv.setText(1+"/"+mNewsPhotoDetail.getPictures().size()+"  "+title);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int pos = mCardScaleHelper.getCurrentItemPos();
                String title = mNewsPhotoDetail.getPictures().get(pos).getTitle() == null ? "" : mNewsPhotoDetail.getPictures().get(pos).getTitle();
                if(mPhotoDetailTitleTv!=null){
                    mPhotoDetailTitleTv.setText(pos+1+"/"+mNewsPhotoDetail.getPictures().size()+"  "+title);
                }
            }

        });
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }



}
