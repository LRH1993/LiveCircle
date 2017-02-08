package com.lvr.livecircle.news.view;

import com.lvr.livecircle.news.model.bean.NewsChannelTable;

import java.util.List;

/**
 * Created by lvr on 2017/2/7.
 */

public interface NewsView {
    void returnNewsChannel(List<NewsChannelTable> tables);
}
