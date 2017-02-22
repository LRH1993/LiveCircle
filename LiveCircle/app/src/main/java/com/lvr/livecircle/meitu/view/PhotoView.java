package com.lvr.livecircle.meitu.view;

import com.lvr.livecircle.meitu.model.bean.PhotoGirl;

import java.util.List;

/**
 * Created by lvr on 2017/2/20.
 */

public interface PhotoView {
    void returnPhotoList(List<PhotoGirl> photoGirlList);
    void showErrorTip();
}
