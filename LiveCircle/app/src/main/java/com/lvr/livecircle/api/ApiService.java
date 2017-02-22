package com.lvr.livecircle.api;


import com.lvr.livecircle.bean.NewsInfo;
import com.lvr.livecircle.meitu.model.bean.GirlData;
import com.lvr.livecircle.news.model.bean.NewsDetail;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by lvr on 2017/2/8.
 */

public interface ApiService {
    public static final String NEWS_BASE_URL = "http://c.m.163.com/nc/article/";
    public static final String PHOTO_BASE_URL = "http://gank.io/api/data/福利/";
    @GET("{type}/{id}/{startPage}-20.html")
    Observable<Map<String, List<NewsInfo>>> getNewsList(
            @Path("type") String type, @Path("id") String id,
            @Path("startPage") int startPage);

    @GET("{postId}/full.html")
    Observable<Map<String, NewsDetail>> getNewDetail(
            @Path("postId") String postId);
    @GET
    Observable<ResponseBody> getNewsBodyHtmlPhoto(
            @Url String photoPath);
    @GET("{size}/{page}")
    Observable<GirlData> getPhotoList(@Path("size") int size, @Path("page") int page);
}
