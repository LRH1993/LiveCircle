package com.lvr.livecircle.bean;

/**
 * Created by lvr on 2017/2/12.
 */

public class FabScrollBean {
    private String scrollMsg;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    private int position;


    public FabScrollBean(String scrollMsg,int position) {
        this.scrollMsg = scrollMsg;
        this.position = position;
    }

    public String getScrollMsg() {
        return scrollMsg;
    }

    public void setScrollMsg(String scrollMsg) {
        this.scrollMsg = scrollMsg;
    }
}
