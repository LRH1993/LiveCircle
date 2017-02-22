package com.lvr.livecircle.meitu.model.impl;

import com.lvr.livecircle.api.ApiService;
import com.lvr.livecircle.app.AppApplication;
import com.lvr.livecircle.client.RetrofitClient;
import com.lvr.livecircle.meitu.model.PhotoModel;
import com.lvr.livecircle.meitu.model.bean.GirlData;
import com.lvr.livecircle.meitu.model.bean.PhotoGirl;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by lvr on 2017/2/20.
 */

public class PhotoModelImpl implements PhotoModel {
    @Override
    public Observable<List<PhotoGirl>> getPhotoList(int size, int page) {
        RetrofitClient client = RetrofitClient.getInstance(AppApplication.getAppContext(), ApiService.PHOTO_BASE_URL);
        ApiService api = client.create(ApiService.class);

        return api.getPhotoList(size,page).map(new Function<GirlData, List<PhotoGirl>>() {
            @Override
            public List<PhotoGirl> apply(GirlData data) throws Exception {
                return  data.getResults();
            }
        }).compose(RetrofitClient.schedulersTransformer);
    }
}
