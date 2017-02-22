package com.lvr.livecircle.news.presenter;

import com.lvr.livecircle.news.model.bean.NewsChannelTable;

import java.util.ArrayList;

/**
 * Created by lvr on 2017/2/14.
 */

public interface NewsChannelPresenter {
    void lodeChannelsRequest();

    void onItemSwap(ArrayList<NewsChannelTable> newsChannelTableList);

    void onItemAddOrRemove(ArrayList<NewsChannelTable> mineChannelTableList, ArrayList<NewsChannelTable> moreChannelTableList);
}
