package com.lvr.livecircle.news.model.impl;

import com.lvr.livecircle.api.ApiService;
import com.lvr.livecircle.app.AppApplication;
import com.lvr.livecircle.client.RetrofitClient;
import com.lvr.livecircle.news.model.NewsDetailModel;
import com.lvr.livecircle.news.model.bean.NewsDetail;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by lvr on 2017/2/11.
 */

public class NewsDetailModelImpl implements NewsDetailModel {
    @Override
    public Observable<NewsDetail> getDetailNews(final String postId) {
        RetrofitClient retrofitClient = RetrofitClient.getInstance(AppApplication.getAppContext(), ApiService.NEWS_BASE_URL);
        ApiService api = retrofitClient.create(ApiService.class);
        return api.getNewDetail(postId).map(new Function<Map<String,NewsDetail>, Object>() {
            @Override
            public Object apply(Map<String, NewsDetail> map) throws Exception {
                NewsDetail newsDetail = map.get(postId);
                //把body中空的图片连接换成img中的
                changeNewsDetail(newsDetail);
                return newsDetail;
            }
        }).compose(RetrofitClient.schedulersTransformer);
    }
    private void changeNewsDetail(NewsDetail newsDetail) {
        List<NewsDetail.ImgBean> imgSrcs = newsDetail.getImg();
        if (isChange(imgSrcs)) {
            String newsBody = newsDetail.getBody();
            newsBody = changeNewsBody(imgSrcs, newsBody);
            newsDetail.setBody(newsBody);
        }
    }

    private boolean isChange(List<NewsDetail.ImgBean> imgSrcs) {
        return imgSrcs != null && imgSrcs.size() >= 2;
    }

    private String changeNewsBody(List<NewsDetail.ImgBean> imgSrcs, String newsBody) {
        for (int i = 0; i < imgSrcs.size(); i++) {
            String oldChars = "<!--IMG#" + i + "-->";
            String newChars;
            if (i == 0) {
                newChars = "";
            } else {
                newChars = "<img src=\"" + imgSrcs.get(i).getSrc() + "\" />";
            }
            newsBody = newsBody.replace(oldChars, newChars);

        }
        return newsBody;
    }
}
