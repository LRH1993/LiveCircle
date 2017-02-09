package com.lvr.livecircle.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lvr.livecircle.R;
import com.lvr.livecircle.bean.NewsInfo;
import com.lvr.livecircle.news.model.bean.NewsChannelTable;
import com.lvr.livecircle.utils.DisplayUtil;
import com.lvr.livecircle.utils.ImageLoaderUtils;
import com.lvr.livecircle.utils.ToastUitl;

import java.util.List;

import static com.lvr.livecircle.R.id.news_summary_photo_iv_group;

/**
 * Created by lvr on 2017/2/8.
 */

public class NewsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private final LayoutInflater inflater;
    private List<NewsInfo> list;
    private List<NewsChannelTable> mTables;
    //新闻模式
    private static final int TYPE_SINGLE = 0;
    //图集模式
    private static final int TYPE_MULTI = 1;
    //头布局模式
    private static final int TYPE_HEADER = 2;

    public NewsListAdapter(Context context, List<NewsInfo> list, List<NewsChannelTable> tables) {
        this.context = context;
        this.list = list;
        this.mTables = tables;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        NewsInfo info = list.get(position);
        if(info.getTitle().endsWith("头布局")){
            return TYPE_HEADER;
        }
        if (!TextUtils.isEmpty(info.getDigest()))
        {
            return TYPE_SINGLE;
        }
        return TYPE_MULTI;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_SINGLE:
                return new SingleViewHolder(inflater.inflate(R.layout.item_news, parent, false));
            case TYPE_MULTI:
                return new MultiViewHolder(inflater.inflate(R.layout.item_news_photo, parent, false));
            case TYPE_HEADER:
                return new HeaderViewHolder(inflater.inflate(R.layout.item_news_header,parent,false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //新闻模式
        if (holder instanceof SingleViewHolder) {
            setNormalItemValues((SingleViewHolder) holder,position);
        }else if(holder instanceof  MultiViewHolder){
            setMultiItemValues((MultiViewHolder) holder,position);
        }else{
            setHeaderItemValues((HeaderViewHolder) holder);
        }
    }

    //设置头布局的值
    private void setHeaderItemValues(HeaderViewHolder holder) {
        NewsChannelGridAdapter mAdapter = new NewsChannelGridAdapter(mTables, context);
        holder.mGridView.setAdapter(mAdapter);
        //解决GridView只显示一行的原因 主动设置GridView的高度
        ViewGroup.LayoutParams params = holder.mGridView.getLayoutParams();
        View view = mAdapter.getView(0, null, holder.mGridView);
        view.measure(0,0);
        int height =view.getMeasuredHeight();
        int totalHeight = holder.mGridView.getVerticalSpacing() * 2 + height * 2;
        params.height = totalHeight;
        holder.mGridView.setLayoutParams(params);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //新闻模式下item值的设置
    private void setNormalItemValues(SingleViewHolder holder, int position) {
        NewsInfo newsInfo = list.get(position);
        String title = newsInfo.getTitle();
        String content = newsInfo.getDigest();
        String time = newsInfo.getPtime();
        String imgsrc = newsInfo.getImgsrc();
        holder.mTitle.setText(title==null?"新闻头条":title);
        holder.mContent.setText(content);
        holder.mTime.setText(time);
        ImageLoaderUtils.display(context,holder.mImageView,imgsrc);
    }

    //图集模式下item值的设置
    private void setMultiItemValues(MultiViewHolder holder, int position) {
        NewsInfo newsInfo = list.get(position);
        String title = newsInfo.getTitle();
        String time = newsInfo.getPtime();
        holder.mTitle.setText(title==null?"新闻头条":title);
        holder.mTime.setText(time);
        setImageView(holder,newsInfo);
    }

    //根据图片的张数设置不同的显示模式
    private void setImageView(MultiViewHolder holder, NewsInfo newsInfo) {
        //根据图片张数确定图片的高度
        int PhotoThreeHeight = (int) DisplayUtil.dip2px(90);
        int PhotoTwoHeight = (int) DisplayUtil.dip2px(120);
        int PhotoOneHeight = (int) DisplayUtil.dip2px(150);
        //存放图片url
        String imgSrcLeft = null;
        String imgSrcMiddle = null;
        String imgSrcRight = null;
        LinearLayout layout = holder.mLayout;
        ViewGroup.LayoutParams params = layout.getLayoutParams();
        //先从ads域中拿图片
        if (newsInfo.getAds() != null) {
            List<NewsInfo.AdsBean> adsBeanList = newsInfo.getAds();
            int size = adsBeanList.size();
            if (size >= 3) {
                //图片数量大于3  只取前三个
                imgSrcLeft = adsBeanList.get(0).getImgsrc();
                imgSrcMiddle = adsBeanList.get(1).getImgsrc();
                imgSrcRight = adsBeanList.get(2).getImgsrc();
                params.height = PhotoThreeHeight;
            } else if (size >= 2) {
                //图片数量2个
                imgSrcLeft = adsBeanList.get(0).getImgsrc();
                imgSrcMiddle = adsBeanList.get(1).getImgsrc();
                params.height = PhotoTwoHeight;
            } else if (size >= 1) {
                //图片数量1个
                imgSrcLeft = adsBeanList.get(0).getImgsrc();
                params.height = PhotoOneHeight;
            }
            //从imgextra域中拿图片
        } else if (newsInfo.getImgextra() != null) {
            int size = newsInfo.getImgextra().size();
            if (size >= 3) {
                imgSrcLeft = newsInfo.getImgextra().get(0).getImgsrc();
                imgSrcMiddle = newsInfo.getImgextra().get(1).getImgsrc();
                imgSrcRight = newsInfo.getImgextra().get(2).getImgsrc();
                params.height = PhotoThreeHeight;
            } else if (size >= 2) {
                imgSrcLeft = newsInfo.getImgextra().get(0).getImgsrc();
                imgSrcMiddle = newsInfo.getImgextra().get(1).getImgsrc();

                params.height = PhotoTwoHeight;
            } else if (size >= 1) {
                imgSrcLeft = newsInfo.getImgextra().get(0).getImgsrc();
                params.height = PhotoOneHeight;
            }
        } else {
            //从imgsrc域中拿图片
            imgSrcLeft = newsInfo.getImgsrc();
            params.height = PhotoOneHeight;
        }
        layout.setLayoutParams(params);
        setPhotoImageView(holder, imgSrcLeft, imgSrcMiddle, imgSrcRight);
    }

    //将拿到的图片加载显示
    private void setPhotoImageView(MultiViewHolder holder, String left, String middle, String right) {
        if (left != null) {
            holder.mIvLeft.setVisibility(View.VISIBLE);
            ImageLoaderUtils.display(context,holder.mIvLeft,left);
        } else {
            holder.mIvLeft.setVisibility(View.GONE);//设置为Gone不占位
        }
        if (middle != null) {
            holder.mIvMiddle.setVisibility(View.VISIBLE);
            ImageLoaderUtils.display(context,holder.mIvMiddle,middle);
        } else {
            holder.mIvMiddle.setVisibility(View.GONE);//设置为Gone不占位
        }
        if (right != null) {
            holder.mIvRight.setVisibility(View.VISIBLE);
            ImageLoaderUtils.display(context,holder.mIvRight,right);
        } else {
            holder.mIvRight.setVisibility(View.GONE);//设置为Gone不占位
        }
    }



    public class SingleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView mImageView;
        private TextView mTitle;
        private TextView mContent;
        private TextView mTime;
        private RelativeLayout mLayout;
        public SingleViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.news_summary_photo_iv);
            mTitle = (TextView) itemView.findViewById(R.id.news_summary_title_tv);
            mContent = (TextView) itemView.findViewById(R.id.news_summary_digest_tv);
            mTime = (TextView) itemView.findViewById(R.id.news_summary_ptime_tv);
            mLayout = (RelativeLayout) itemView.findViewById(R.id.rl_root);
            mLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context,"点击了",Toast.LENGTH_SHORT).show();
        }
    }
    public class MultiViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private LinearLayout mLayout;
        private TextView mTitle;
        private TextView mTime;
        private ImageView mIvLeft;
        private ImageView mIvMiddle;
        private ImageView mIvRight;
        private LinearLayout mRoot;

        public MultiViewHolder(View itemView) {
            super(itemView);
            mLayout = (LinearLayout) itemView.findViewById(news_summary_photo_iv_group);
            mTitle = (TextView) itemView.findViewById(R.id.news_summary_title_tv);
            mTime = (TextView) itemView.findViewById(R.id.news_summary_ptime_tv);
            mIvLeft = (ImageView) itemView.findViewById(R.id.news_summary_photo_iv_left);
            mIvMiddle = (ImageView) itemView.findViewById(R.id.news_summary_photo_iv_middle);
            mIvRight = (ImageView) itemView.findViewById(R.id.news_summary_photo_iv_right);
            mRoot = (LinearLayout) itemView.findViewById(R.id.ll_root);
            mRoot.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context,"点击了",Toast.LENGTH_SHORT).show();
        }
    }
    public class HeaderViewHolder extends RecyclerView.ViewHolder{
        private GridView mGridView;
        public HeaderViewHolder(View itemView) {
            super(itemView);
            mGridView = (GridView) itemView.findViewById(R.id.gv_news_channel);
            mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    String name = mTables.get(position).getNewsChannelName();
                    ToastUitl.showShort(name);
                }
            });
        }
    }
}
