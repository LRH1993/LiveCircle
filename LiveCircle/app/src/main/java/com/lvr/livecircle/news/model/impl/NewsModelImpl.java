package com.lvr.livecircle.news.model.impl;

import com.lvr.livecircle.app.AppApplication;
import com.lvr.livecircle.app.AppConstant;
import com.lvr.livecircle.news.db.NewsChannelTableManager;
import com.lvr.livecircle.news.model.NewsModel;
import com.lvr.livecircle.news.model.bean.NewsChannelTable;
import com.lvr.livecircle.utils.ACache;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lvr on 2017/2/7.
 */

public class NewsModelImpl implements NewsModel {
    @Override
    public Observable<List<NewsChannelTable>> requestNewsChannels() {
        return Observable.create(new ObservableOnSubscribe<List<NewsChannelTable>>() {
            @Override
            public void subscribe(ObservableEmitter<List<NewsChannelTable>> e) throws Exception {
                ArrayList<NewsChannelTable> newsChannelTableList = (ArrayList<NewsChannelTable>) ACache.get(AppApplication.getAppContext()).getAsObject(AppConstant.CHANNEL_MINE);
                if(newsChannelTableList==null){
                    newsChannelTableList= (ArrayList<NewsChannelTable>) NewsChannelTableManager.loadNewsChannelsStatic();
                    ACache.get(AppApplication.getAppContext()).put(AppConstant.CHANNEL_MINE,newsChannelTableList);
                }
                e.onNext(newsChannelTableList);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io());
    }
}
