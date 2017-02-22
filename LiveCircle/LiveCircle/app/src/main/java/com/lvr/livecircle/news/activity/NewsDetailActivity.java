package com.lvr.livecircle.news.activity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lvr.livecircle.R;
import com.lvr.livecircle.app.AppConstant;
import com.lvr.livecircle.base.BaseActivity;
import com.lvr.livecircle.client.RetrofitClient;
import com.lvr.livecircle.client.RxDisposeManager;
import com.lvr.livecircle.news.model.bean.NewsDetail;
import com.lvr.livecircle.news.presenter.impl.NewsDetailPresenterImpl;
import com.lvr.livecircle.news.view.NewsDetailView;
import com.lvr.livecircle.utils.ImageLoaderUtils;
import com.lvr.livecircle.utils.StatusBarSetting;
import com.lvr.livecircle.utils.TimeUtil;
import com.lvr.livecircle.widget.URLImageGetter;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 普通新闻详情
 */
public class NewsDetailActivity extends BaseActivity implements NewsDetailView {

    @BindView(R.id.news_detail_photo_iv)
    ImageView mNewsDetailPhotoIv;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout mToolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBar;
    @BindView(R.id.news_detail_from_tv)
    TextView mNewsDetailFromTv;
    @BindView(R.id.news_detail_body_tv)
    TextView mNewsDetailBodyTv;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    private String postId;
    private String mNewsTitle;
    private String mShareLink;
    private NewsDetailPresenterImpl mPresenter;
    private Context mContext;
    private URLImageGetter mUrlImageGetter;
    /**
     * 入口
     *
     * @param mContext
     * @param postId
     */
    public static void startAction(Context mContext, View view, String postId, String imgUrl) {
        Intent intent = new Intent(mContext, NewsDetailActivity.class);
        intent.putExtra(AppConstant.NEWS_POST_ID, postId);
        intent.putExtra(AppConstant.NEWS_IMG_RES, imgUrl);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions
                    .makeSceneTransitionAnimation((Activity) mContext, view, AppConstant.TRANSITION_ANIMATION_NEWS_PHOTOS);
            mContext.startActivity(intent, options.toBundle());
        } else {
            //让新的Activity从一个小的范围扩大到全屏
            ActivityOptionsCompat options = ActivityOptionsCompat
                    .makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
            ActivityCompat.startActivity((Activity) mContext, intent, options.toBundle());
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_news_detail;
    }

    @Override
    public void initPresenter() {
        mPresenter = new NewsDetailPresenterImpl(this);
    }

    @Override
    public void initView() {
        StatusBarSetting.setTranslucent(NewsDetailActivity.this);
        mContext = this;
        postId = getIntent().getStringExtra(AppConstant.NEWS_POST_ID);
        System.out.println(postId);
        mPresenter.requestDetailNews(postId);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void returnNewsDetail(NewsDetail detail) {
        System.out.println("返回的数据："+detail);
        mShareLink = detail.getShareLink();
        mNewsTitle = detail.getTitle();
        String newsSource = detail.getSource();
        String newsTime = TimeUtil.formatDate(detail.getPtime());
        String newsBody = detail.getBody();
        String NewsImgSrc = getImgSrcs(detail);
        setToolBarLayout(mNewsTitle);
        mNewsDetailFromTv.setText(getString(R.string.news_from, newsSource, newsTime));
        setNewsDetailPhotoIv(NewsImgSrc);
        setNewsDetailBodyTv(detail, newsBody);
    }

    //设置新闻主体内容
    private void setNewsDetailBodyTv(final NewsDetail detail, final String body) {
        Observable.timer(1, TimeUnit.MILLISECONDS)
                .compose(RetrofitClient.schedulersTransformer)
                .subscribe(new Observer() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        RxDisposeManager.get().add("newsBody",d);
                        mProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onNext(Object o) {
                        mProgressBar.setVisibility(View.VISIBLE);
                        setBody(detail, body);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onComplete() {
                        mProgressBar.setVisibility(View.GONE);
                    }
                });
    }

    private void setBody(NewsDetail detail, String body) {
        int imgTotal = detail.getImg().size();
        if (isShowBody(body, imgTotal)) {
            mUrlImageGetter = new URLImageGetter(mNewsDetailBodyTv, body, imgTotal);
            mNewsDetailBodyTv.setText(Html.fromHtml(body, mUrlImageGetter, null));
        } else {
            mNewsDetailBodyTv.setText(Html.fromHtml(body));
        }
    }

    private boolean isShowBody(String body, int total) {
        return total >= 2 && body != null;
    }

    //加载图片
    private void setNewsDetailPhotoIv(String src) {
        ImageLoaderUtils.displayBigPhoto(mContext,mNewsDetailPhotoIv,src);
    }

    //设置toolbar内容
    private void setToolBarLayout(String title) {
        mToolbarLayout.setTitle(title);
        mToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, R.color.white));
        mToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.primary_text_white));
    }

    //获取图片资源
    private String getImgSrcs(NewsDetail newsDetail) {
        List<NewsDetail.ImgBean> imgSrcs = newsDetail.getImg();
        String imgSrc;
        if (imgSrcs != null && imgSrcs.size() > 0) {
            imgSrc = imgSrcs.get(0).getSrc();
        } else {
            imgSrc = getIntent().getStringExtra(AppConstant.NEWS_IMG_RES);
        }
        return imgSrc;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxDisposeManager.get().cancel("newsBody");
        RxDisposeManager.get().cancel("newsDetail");

    }
}
