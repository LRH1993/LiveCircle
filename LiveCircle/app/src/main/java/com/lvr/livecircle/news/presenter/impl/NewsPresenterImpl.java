package com.lvr.livecircle.news.presenter.impl;

import com.lvr.livecircle.bean.NewsInfo;
import com.lvr.livecircle.client.RxDisposeManager;
import com.lvr.livecircle.news.fragment.NewsFragment;
import com.lvr.livecircle.news.model.bean.NewsChannelTable;
import com.lvr.livecircle.news.model.impl.NewsModelImpl;
import com.lvr.livecircle.news.presenter.NewPresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by lvr on 2017/2/7.
 */

public class NewsPresenterImpl  implements NewPresenter {
    private NewsFragment mNewsView;
    private NewsModelImpl mNewsModel;
    private List<NewsInfo> mInfos = new ArrayList<NewsInfo>();
    public NewsPresenterImpl(NewsFragment view) {
        this.mNewsView = view;
        this.mNewsModel = new NewsModelImpl();
    }

    @Override
    public void lodeMineChannelsRequest() {
        Observable<List<NewsChannelTable>> observable = mNewsModel.requestNewsChannels();
        observable.observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mNewsView.startProgressDialog();
                    }
                }).subscribe(new Observer<List<NewsChannelTable>>() {
            @Override
            public void onSubscribe(Disposable d) {
                RxDisposeManager.get().add("newschannel",d);
            }

            @Override
            public void onNext(List<NewsChannelTable> tables) {
                mNewsView.returnNewsChannel(tables);
            }

            @Override
            public void onError(Throwable e) {
                mNewsView.stopProgressDialog();
                mNewsView.showNetErrorTip("内部错误");
            }

            @Override
            public void onComplete() {
                mNewsView.stopProgressDialog();
            }
        });
    }

    @Override
    public void loadNewsListRequest(String newsType, final String id, int startPage) {
        NewsInfo newsInfo= new NewsInfo();
        newsInfo.setTitle("头布局");
        mInfos.add(newsInfo);
        Observable<NewsInfo> observable = mNewsModel.requestNewsList(newsType, id, startPage);
        observable.doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                mNewsView.startProgressDialog();
            }
        }).subscribe(new Observer<NewsInfo>() {
            @Override
            public void onSubscribe(Disposable d) {
                RxDisposeManager.get().add("newslist", d);
            }

            @Override
            public void onNext(NewsInfo info) {
                mInfos.add(info);

            }

            @Override
            public void onError(Throwable e) {
                mNewsView.stopProgressDialog();
                mNewsView.showNetErrorTip("内部错误");
            }

            @Override
            public void onComplete() {
                mNewsView.stopProgressDialog();
                mNewsView.returnNewsList(mInfos);
            }
        });
    }
}
