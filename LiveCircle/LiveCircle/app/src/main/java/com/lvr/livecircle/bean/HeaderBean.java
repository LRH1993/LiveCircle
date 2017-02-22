package com.lvr.livecircle.bean;

import com.lvr.livecircle.news.model.bean.NewsChannelTable;

import java.util.List;

/**
 * Created by lvr on 2017/2/14.
 */

public class HeaderBean {
    private List<NewsChannelTable> mList;

    public HeaderBean(List<NewsChannelTable> list) {
        mList = list;
    }

    public List<NewsChannelTable> getList() {
        return mList;
    }

    public void setList(List<NewsChannelTable> list) {
        mList = list;
    }
}
