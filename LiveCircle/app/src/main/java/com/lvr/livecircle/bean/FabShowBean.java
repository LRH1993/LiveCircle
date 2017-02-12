package com.lvr.livecircle.bean;

/**
 * Created by lvr on 2017/2/12.
 * Fab是否显示
 */

public class FabShowBean {
    private boolean isShow;

    public FabShowBean(boolean isShow) {
        this.isShow = isShow;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }
}
