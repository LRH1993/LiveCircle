package com.lvr.livecircle.news.presenter;

/**
 * Created by lvr on 2017/2/7.
 */

public interface NewPresenter {
    void lodeMineChannelsRequest();
    void loadNewsListRequest(String newsType, final String id, int startPage);
}
