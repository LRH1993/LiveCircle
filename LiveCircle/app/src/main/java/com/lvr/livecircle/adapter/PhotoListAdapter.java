package com.lvr.livecircle.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lvr.livecircle.R;
import com.lvr.livecircle.meitu.model.bean.PhotoGirl;
import com.lvr.livecircle.utils.ImageLoaderUtils;

import java.util.List;

/**
 * Created by lvr on 2017/2/20.
 */

public class PhotoListAdapter extends RecyclerView.Adapter<PhotoListAdapter.PhotoViewHolder> {
    private  Context mContext;
    private List<PhotoGirl> mData;
    private final LayoutInflater inflater;
    public PhotoListAdapter(Context context, List<PhotoGirl> list) {
        this.mContext = context;
        this.inflater = LayoutInflater.from(context);
        this.mData = list;
    }


    @Override
    public PhotoListAdapter.PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PhotoViewHolder(inflater.inflate(R.layout.item_photo_girl,parent,false));
    }

    @Override
    public void onBindViewHolder(PhotoListAdapter.PhotoViewHolder holder, int position) {
        ImageLoaderUtils.display(mContext,holder.mImageView,mData.get(position).getUrl());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    public class PhotoViewHolder extends RecyclerView.ViewHolder{
        private ImageView mImageView;
        public PhotoViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_photo);
        }

    }
    public List<PhotoGirl> getAdapterData(){
        return mData;
    }
}
