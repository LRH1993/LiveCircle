package com.lvr.livecircle.news.model;

import com.lvr.livecircle.news.model.bean.NewsDetail;

import io.reactivex.Observable;

/**
 * Created by lvr on 2017/2/11.
 */

public interface NewsDetailModel {
    Observable<NewsDetail> getDetailNews(String postId);
}
