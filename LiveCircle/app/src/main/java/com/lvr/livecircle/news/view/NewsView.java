package com.lvr.livecircle.news.view;

import com.lvr.livecircle.bean.NewsInfo;
import com.lvr.livecircle.news.model.bean.NewsChannelTable;

import java.util.List;

/**
 * Created by lvr on 2017/2/7.
 */

public interface NewsView {
    void returnNewsChannel(List<NewsChannelTable> tables);
    void returnNewsList(List<NewsInfo> info);
}
