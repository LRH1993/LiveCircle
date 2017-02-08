package com.lvr.livecircle.news.model;

import com.lvr.livecircle.news.model.bean.NewsChannelTable;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by lvr on 2017/2/7.
 */

public interface NewsModel {
    Observable<List<NewsChannelTable>> requestNewsChannels();
}
