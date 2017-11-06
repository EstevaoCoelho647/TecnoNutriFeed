package com.coelho.estevao.tecnonutrifeed;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by estevao on 06/11/17.
 */

public class FeedItemAdapter extends RecyclerView.Adapter<FeedItemAdapter.MyViewHolder> {

    List<FeedItem> feedItems;

    public FeedItemAdapter() {
        this.feedItems = new ArrayList<>();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        FeedItem feedItem = feedItems.get(position);
        holder.textViewDescription.setText(feedItem.getDate());
        holder.textViewKcal.setText(feedItem.getEnergy());

        Picasso.with(holder.postImageView.getContext())
                .load(feedItem.getImage())
                .into(holder.postImageView);


        Picasso.with(holder.imageViewuserImage.getContext())
                .load(feedItem.getProfile().getImage())
                .into(holder.imageViewuserImage);

        holder.textViewUserName.setText(feedItem.getProfile().getName());
        holder.textViewUserSubtitle.setText(feedItem.getProfile().getGeneralGoal());

    }

    @Override
    public int getItemCount() {
        return feedItems.size();
    }

    public List<FeedItem> getFeedItems() {
        return feedItems;
    }

    public void setFeedItems(List<FeedItem> feedItems) {
        this.feedItems.clear();
        this.feedItems.addAll(feedItems);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textViewDescription)
        public TextView textViewDescription;
        @BindView(R.id.imageViewuserImage)
        public ImageView imageViewuserImage;
        @BindView(R.id.textViewUserName)
        public TextView textViewUserName;
        @BindView(R.id.textViewUserSubtitle)
        public TextView textViewUserSubtitle;
        @BindView(R.id.postImageView)
        public ImageView postImageView;
        @BindView(R.id.textViewKcal)
        public TextView textViewKcal;

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
