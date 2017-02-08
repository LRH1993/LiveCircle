package com.lvr.livecircle.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lvr.livecircle.R;
import com.lvr.livecircle.news.model.bean.NewsChannelTable;

import java.util.List;

/**
 * Created by lvr on 2017/2/7.
 */

public class NewsChannelGridAdapter extends BaseAdapter {
    private List<NewsChannelTable> mTables;
    private Context mContext;
    private LayoutInflater mInflater;

    public NewsChannelGridAdapter(List<NewsChannelTable> mTables, Context context) {
        this.mTables = mTables;
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mTables.size();
    }

    @Override
    public Object getItem(int i) {
        return mTables.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup group) {
        View view = null;
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder = new ViewHolder();
            view = mInflater.inflate(R.layout.item_grid_news_channel,group,false);
            viewHolder.mImageView = (ImageView) view.findViewById(R.id.iv_channel_logo);
            viewHolder.mTextView= (TextView) view.findViewById(R.id.tv_channel_name);
            view.setTag(viewHolder);
        }else{
           view =  convertView;
           viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.mImageView.setImageResource(mTables.get(position).getImgRes());
        viewHolder.mTextView.setText(mTables.get(position).getNewsChannelName());
        return view;
    }

    public static class ViewHolder {
        ImageView mImageView;
        TextView mTextView;
    }
}
