package com.lvr.livecircle.bean;

/**
 * Created by lvr on 2017/2/6.
 */

public class FloatMsg {
    private String msg;
    private boolean isShow;
    public FloatMsg(String msg,boolean isShow) {
        this.msg = msg;
        this.isShow = isShow;
    }
    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }



    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
