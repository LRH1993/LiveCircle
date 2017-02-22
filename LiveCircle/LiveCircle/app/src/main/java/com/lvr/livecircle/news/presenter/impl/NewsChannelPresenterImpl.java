package com.lvr.livecircle.news.presenter.impl;

import com.lvr.livecircle.bean.HeaderBean;
import com.lvr.livecircle.client.RxDisposeManager;
import com.lvr.livecircle.news.activity.NewsChannelActivity;
import com.lvr.livecircle.news.model.bean.NewsChannelTable;
import com.lvr.livecircle.news.model.impl.NewsChannelModelImpl;
import com.lvr.livecircle.news.model.impl.NewsModelImpl;
import com.lvr.livecircle.news.presenter.NewsChannelPresenter;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by lvr on 2017/2/14.
 */

public class NewsChannelPresenterImpl implements NewsChannelPresenter {
    private NewsChannelActivity mView;
    private NewsChannelModelImpl mModel;
    private NewsModelImpl mNewsModel;

    public NewsChannelPresenterImpl(NewsChannelActivity activity) {
        this.mView = activity;
        this.mModel = new NewsChannelModelImpl();
        this.mNewsModel = new NewsModelImpl();
    }

    @Override
    public void lodeChannelsRequest() {
        Observable<List<NewsChannelTable>> observable = mNewsModel.requestNewsChannels();
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<NewsChannelTable>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        RxDisposeManager.get().add("newsMinechannel", d);

                    }

                    @Override
                    public void onNext(List<NewsChannelTable> tables) {
                        mView.returnMineNewsChannels(tables);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        Observable<List<NewsChannelTable>> moreObservable = mModel.lodeMoreNewsChannels();
        moreObservable.subscribe(new Observer<List<NewsChannelTable>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        RxDisposeManager.get().add("newsMorechannel", d);

                    }

                    @Override
                    public void onNext(List<NewsChannelTable> tables) {
                        mView.returnMoreNewsChannels(tables);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onItemSwap(final ArrayList<NewsChannelTable> newsChannelTableList) {
        mModel.swapDb(newsChannelTableList).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                EventBus.getDefault().post(new HeaderBean(newsChannelTableList));
            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void onItemAddOrRemove(final ArrayList<NewsChannelTable> mineChannelTableList, ArrayList<NewsChannelTable> moreChannelTableList) {
        mModel.updateDb(mineChannelTableList,moreChannelTableList).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                //发送消息 通知NewsFragment更改头布局信息
                EventBus.getDefault().post(new HeaderBean(mineChannelTableList));
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
