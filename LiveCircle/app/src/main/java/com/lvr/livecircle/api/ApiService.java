package com.lvr.livecircle.api;


import com.lvr.livecircle.bean.NewsInfo;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by lvr on 2017/2/8.
 */

public interface ApiService {
    public static final String NEWS_BASE_URL = "http://c.m.163.com/nc/article/";

    @GET("{type}/{id}/{startPage}-20.html")
    Observable<Map<String, List<NewsInfo>>> getNewsList(
            @Path("type") String type, @Path("id") String id,
            @Path("startPage") int startPage);
}
