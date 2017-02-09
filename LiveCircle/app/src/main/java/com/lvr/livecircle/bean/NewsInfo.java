package com.lvr.livecircle.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvr on 2017/2/8.
 */
//使用GsonFormat插件 使用Parcelable插件
public class NewsInfo implements Parcelable {

    /**
     * postid : PHOT24589000100A
     * hasCover : false
     * hasHead : 1
     * replyCount : 32056
     * hasImg : 1
     * digest :
     * hasIcon : false
     * docid : 9IG74V5H00963VRO_CCP0IVDUcaoruiupdateDoc
     * title : 韩民众举"包青天"头像 抗议总统弹劾案
     * order : 1
     * priority : 354
     * lmodify : 2017-02-08 16:14:08
     * boardid : photoview_bbs
     * ads : [{"title":"直击云南边境扫雷 地雷炮弹摆满地","tag":"photoset","imgsrc":"http://cms-bucket.nosdn.127.net/e4c85b6a0db74b139b0c40572181883a20170208163525.jpeg","subtitle":"","url":"00AP0001|2233612"},{"title":"特朗普任后首批被遣墨西哥移民回国","tag":"photoset","imgsrc":"http://cms-bucket.nosdn.127.net/40dd56cbd22043519fc7bdbb95cc954820170208164609.jpeg","subtitle":"","url":"00AO0001|2233614"},{"title":"巴西警察罢工引发骚乱 已致75人死亡","tag":"photoset","imgsrc":"http://cms-bucket.nosdn.127.net/0c27e0f72fab46c08c4399e6146cc4a120170208163826.jpeg","subtitle":"","url":"00AO0001|2233613"},{"title":"大圣驾车被交警拦下 车上坐一群\"妖怪\"","tag":"photoset","imgsrc":"http://cms-bucket.nosdn.127.net/376884a7cff14a95839f23a032c3f60620170208100814.jpeg","subtitle":"","url":"00AP0001|2233503"},{"title":"广东揭阳江中现观音像 村民打捞参拜","tag":"photoset","imgsrc":"http://cms-bucket.nosdn.127.net/f7fb6acf5e424a12aeaacb7b6192006320170208093602.jpeg","subtitle":"","url":"00AP0001|2233473"}]
     * photosetID : 00AO0001|2233609
     * imgsum : 4
     * topic_background : http://img2.cache.netease.com/m/newsapp/reading/cover1/C1348646712614.jpg
     * template : normal1
     * votecount : 29790
     * skipID : 00AO0001|2233609
     * alias : Top News
     * skipType : photoset
     * cid : C1348646712614
     * hasAD : 1
     * imgextra : [{"imgsrc":"http://cms-bucket.nosdn.127.net/3e8167782a344c47886dd4b818274e3720170208161145.jpeg"},{"imgsrc":"http://cms-bucket.nosdn.127.net/1bef3f98431f4be780320ce58d7c68d920170208160645.jpeg"}]
     * source : 网易原创
     * ename : androidnews
     * tname : 头条
     * imgsrc : http://cms-bucket.nosdn.127.net/37148e8af39440be9b41606f72dc49d520170208161145.jpeg
     * ptime : 2017-02-08 16:07:25
     */

    private String postid;
    private boolean hasCover;
    private int hasHead;
    private int replyCount;
    private int hasImg;
    private String digest;
    private boolean hasIcon;
    private String docid;
    private String title;
    private int order;
    private int priority;
    private String lmodify;
    private String boardid;
    private String photosetID;
    private int imgsum;
    private String topic_background;
    private String template;
    private int votecount;
    private String skipID;
    private String alias;
    private String skipType;
    private String cid;
    private int hasAD;
    private String source;
    private String ename;
    private String tname;
    private String imgsrc;
    private String ptime;
    private List<AdsBean> ads;
    private List<ImgextraBean> imgextra;

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public boolean isHasCover() {
        return hasCover;
    }

    public void setHasCover(boolean hasCover) {
        this.hasCover = hasCover;
    }

    public int getHasHead() {
        return hasHead;
    }

    public void setHasHead(int hasHead) {
        this.hasHead = hasHead;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public int getHasImg() {
        return hasImg;
    }

    public void setHasImg(int hasImg) {
        this.hasImg = hasImg;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public boolean isHasIcon() {
        return hasIcon;
    }

    public void setHasIcon(boolean hasIcon) {
        this.hasIcon = hasIcon;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getLmodify() {
        return lmodify;
    }

    public void setLmodify(String lmodify) {
        this.lmodify = lmodify;
    }

    public String getBoardid() {
        return boardid;
    }

    public void setBoardid(String boardid) {
        this.boardid = boardid;
    }

    public String getPhotosetID() {
        return photosetID;
    }

    public void setPhotosetID(String photosetID) {
        this.photosetID = photosetID;
    }

    public int getImgsum() {
        return imgsum;
    }

    public void setImgsum(int imgsum) {
        this.imgsum = imgsum;
    }

    public String getTopic_background() {
        return topic_background;
    }

    public void setTopic_background(String topic_background) {
        this.topic_background = topic_background;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public int getVotecount() {
        return votecount;
    }

    public void setVotecount(int votecount) {
        this.votecount = votecount;
    }

    public String getSkipID() {
        return skipID;
    }

    public void setSkipID(String skipID) {
        this.skipID = skipID;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getSkipType() {
        return skipType;
    }

    public void setSkipType(String skipType) {
        this.skipType = skipType;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public int getHasAD() {
        return hasAD;
    }

    public void setHasAD(int hasAD) {
        this.hasAD = hasAD;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public List<AdsBean> getAds() {
        return ads;
    }

    public void setAds(List<AdsBean> ads) {
        this.ads = ads;
    }

    public List<ImgextraBean> getImgextra() {
        return imgextra;
    }

    public void setImgextra(List<ImgextraBean> imgextra) {
        this.imgextra = imgextra;
    }

    public static class AdsBean {
        /**
         * title : 直击云南边境扫雷 地雷炮弹摆满地
         * tag : photoset
         * imgsrc : http://cms-bucket.nosdn.127.net/e4c85b6a0db74b139b0c40572181883a20170208163525.jpeg
         * subtitle :
         * url : 00AP0001|2233612
         */

        private String title;
        private String tag;
        private String imgsrc;
        private String subtitle;
        private String url;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getImgsrc() {
            return imgsrc;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class ImgextraBean {
        /**
         * imgsrc : http://cms-bucket.nosdn.127.net/3e8167782a344c47886dd4b818274e3720170208161145.jpeg
         */

        private String imgsrc;

        public String getImgsrc() {
            return imgsrc;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.postid);
        dest.writeByte(this.hasCover ? (byte) 1 : (byte) 0);
        dest.writeInt(this.hasHead);
        dest.writeInt(this.replyCount);
        dest.writeInt(this.hasImg);
        dest.writeString(this.digest);
        dest.writeByte(this.hasIcon ? (byte) 1 : (byte) 0);
        dest.writeString(this.docid);
        dest.writeString(this.title);
        dest.writeInt(this.order);
        dest.writeInt(this.priority);
        dest.writeString(this.lmodify);
        dest.writeString(this.boardid);
        dest.writeString(this.photosetID);
        dest.writeInt(this.imgsum);
        dest.writeString(this.topic_background);
        dest.writeString(this.template);
        dest.writeInt(this.votecount);
        dest.writeString(this.skipID);
        dest.writeString(this.alias);
        dest.writeString(this.skipType);
        dest.writeString(this.cid);
        dest.writeInt(this.hasAD);
        dest.writeString(this.source);
        dest.writeString(this.ename);
        dest.writeString(this.tname);
        dest.writeString(this.imgsrc);
        dest.writeString(this.ptime);
        dest.writeList(this.ads);
        dest.writeList(this.imgextra);
    }

    public NewsInfo() {
    }

    protected NewsInfo(Parcel in) {
        this.postid = in.readString();
        this.hasCover = in.readByte() != 0;
        this.hasHead = in.readInt();
        this.replyCount = in.readInt();
        this.hasImg = in.readInt();
        this.digest = in.readString();
        this.hasIcon = in.readByte() != 0;
        this.docid = in.readString();
        this.title = in.readString();
        this.order = in.readInt();
        this.priority = in.readInt();
        this.lmodify = in.readString();
        this.boardid = in.readString();
        this.photosetID = in.readString();
        this.imgsum = in.readInt();
        this.topic_background = in.readString();
        this.template = in.readString();
        this.votecount = in.readInt();
        this.skipID = in.readString();
        this.alias = in.readString();
        this.skipType = in.readString();
        this.cid = in.readString();
        this.hasAD = in.readInt();
        this.source = in.readString();
        this.ename = in.readString();
        this.tname = in.readString();
        this.imgsrc = in.readString();
        this.ptime = in.readString();
        this.ads = new ArrayList<AdsBean>();
        in.readList(this.ads, AdsBean.class.getClassLoader());
        this.imgextra = new ArrayList<ImgextraBean>();
        in.readList(this.imgextra, ImgextraBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<NewsInfo> CREATOR = new Parcelable.Creator<NewsInfo>() {
        @Override
        public NewsInfo createFromParcel(Parcel source) {
            return new NewsInfo(source);
        }

        @Override
        public NewsInfo[] newArray(int size) {
            return new NewsInfo[size];
        }
    };
}
