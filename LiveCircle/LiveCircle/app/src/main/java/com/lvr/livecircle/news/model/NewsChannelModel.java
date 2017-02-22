package com.lvr.livecircle.news.model;

import com.lvr.livecircle.news.model.bean.NewsChannelTable;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by lvr on 2017/2/14.
 */

public interface NewsChannelModel {
    Observable<List<NewsChannelTable>> lodeMoreNewsChannels();
    Observable<String> swapDb(ArrayList<NewsChannelTable> newsChannelTableList);
    Observable<String> updateDb(ArrayList<NewsChannelTable> mineChannelTableList, ArrayList<NewsChannelTable> moreChannelTableList);
}
