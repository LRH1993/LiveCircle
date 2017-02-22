package com.lvr.livecircle.meitu.presenter.impl;

import com.lvr.livecircle.client.RxDisposeManager;
import com.lvr.livecircle.meitu.MeiTuFragment;
import com.lvr.livecircle.meitu.model.bean.PhotoGirl;
import com.lvr.livecircle.meitu.model.impl.PhotoModelImpl;
import com.lvr.livecircle.meitu.presenter.PhotoPresenter;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by lvr on 2017/2/20.
 */

public class PhotoPresenterImpl implements PhotoPresenter {
    private PhotoModelImpl mModel;
    private MeiTuFragment mView;
    //是否开启了dialog
    private boolean showDialog;
    public PhotoPresenterImpl(MeiTuFragment mView) {
        this.mView = mView;
        this.mModel = new PhotoModelImpl();
    }

    @Override
    public void requestPhotoList(int size, int page) {
        mModel.getPhotoList(size,page).subscribe(new Observer<List<PhotoGirl>>() {
            @Override
            public void onSubscribe(Disposable d) {
                RxDisposeManager.get().add("photoList",d);

            }

            @Override
            public void onNext(List<PhotoGirl> girls) {
                mView.returnPhotoList(girls);
            }

            @Override
            public void onError(Throwable e) {
               mView.showErrorTip();
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
