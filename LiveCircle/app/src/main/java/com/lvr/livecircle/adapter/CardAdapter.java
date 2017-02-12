package com.lvr.livecircle.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lvr.livecircle.R;
import com.lvr.livecircle.news.model.bean.NewsPhotoDetail;
import com.lvr.livecircle.recycleviewcardgallery.CardAdapterHelper;
import com.lvr.livecircle.utils.ImageLoaderUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lvr on 2017/2/6.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private List<NewsPhotoDetail.Picture> mList = new ArrayList<>();
    private CardAdapterHelper mCardAdapterHelper = new CardAdapterHelper();
    private Context mContext;
    public CardAdapter(Context context,List<NewsPhotoDetail.Picture> mList) {
        this.mList = mList;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_view, parent, false);
        mCardAdapterHelper.onCreateViewHolder(parent, itemView);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        mCardAdapterHelper.onBindViewHolder(holder.itemView, position, getItemCount());
        ImageLoaderUtils.displayBigPhoto(mContext,holder.mImageView,mList.get(position).getImgSrc());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView mImageView;

        public ViewHolder(final View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.imageView);
        }

    }


}
