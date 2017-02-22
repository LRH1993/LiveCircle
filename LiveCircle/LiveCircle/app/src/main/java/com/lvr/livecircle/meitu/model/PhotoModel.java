package com.lvr.livecircle.meitu.model;

import com.lvr.livecircle.meitu.model.bean.PhotoGirl;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by lvr on 2017/2/20.
 */

public interface PhotoModel {
    Observable<List<PhotoGirl>> getPhotoList(int size, int page);
}
