package com.lvr.livecircle.news.presenter.impl;

import com.lvr.livecircle.news.fragment.NewsFragment;
import com.lvr.livecircle.news.model.bean.NewsChannelTable;
import com.lvr.livecircle.news.model.impl.NewsModelImpl;
import com.lvr.livecircle.news.presenter.NewPresenter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by lvr on 2017/2/7.
 */

public class NewsPresenterImpl  implements NewPresenter {
    private NewsFragment mNewsView;
    private NewsModelImpl mNewsModel;

    public NewsPresenterImpl(NewsFragment view) {
        this.mNewsView = view;
        this.mNewsModel = new NewsModelImpl();
    }

    @Override
    public void lodeMineChannelsRequest() {
        Observable<List<NewsChannelTable>> observable = mNewsModel.requestNewsChannels();
        observable.observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<NewsChannelTable>>() {
            @Override
            public void onSubscribe(Disposable d) {
                mNewsView.startProgressDialog();
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
}
