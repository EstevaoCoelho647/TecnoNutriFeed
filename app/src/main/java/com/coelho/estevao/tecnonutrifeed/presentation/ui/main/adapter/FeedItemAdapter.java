package com.coelho.estevao.tecnonutrifeed.presentation.ui.main.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coelho.estevao.tecnonutrifeed.R;
import com.coelho.estevao.tecnonutrifeed.domain.entity.Item;
import com.coelho.estevao.tecnonutrifeed.domain.entity.Meal;
import com.coelho.estevao.tecnonutrifeed.domain.persistence.ItemDatabaseRepository;
import com.coelho.estevao.tecnonutrifeed.presentation.ui.main.MainPresenter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by estevao on 06/11/17.
 */

public class FeedItemAdapter extends RecyclerView.Adapter<FeedItemAdapter.MyViewHolder> {

    List<Item> items;
    private MainPresenter mainPresenter;

    public FeedItemAdapter(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
        this.items = new ArrayList<>();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Item item = items.get(position);
        holder.textViewDescription.setText(item.getDate());
        holder.textViewKcal.setText(item.getEnergy() + " Kcal");

        Picasso.with(holder.postImageView.getContext())
                .load(item.getImage()).placeholder(R.drawable.ic_local_pizza)
                .into(holder.postImageView);


        Picasso.with(holder.imageViewuserImage.getContext())
                .load(item.getProfile().getImage()).placeholder(R.drawable.ic_person)
                .into(holder.imageViewuserImage);

        holder.textViewUserName.setText(item.getProfile().getName());
        if (item.getProfile().getGeneralGoal() != null) {
            holder.textViewUserSubtitle.setVisibility(View.VISIBLE);
            holder.textViewUserSubtitle.setText(item.getProfile().getGeneralGoal());
        } else
            holder.textViewUserSubtitle.setVisibility(View.GONE);

        holder.textViewMeal.setText(Meal.values()[item.getMeal()].getValue());

        boolean alreadyLiked = ItemDatabaseRepository.getItemById(item.getFeedHash()).isLiked();
        item.setLiked(alreadyLiked);
        if (alreadyLiked) {
            holder.imageViewFavorite.setScaleX(1);
            holder.imageViewFavorite.setScaleY(1);
        } else {
            holder.imageViewFavorite.setScaleX(0);
            holder.imageViewFavorite.setScaleY(0);
        }

        holder.buttonLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item.isLiked()) {
                    holder.imageViewFavorite.animate().scaleX(0).scaleY(0).start();
                    item.setLiked(false);
                    mainPresenter.onButtonLikeClicked(item, false);
                } else {
                    holder.imageViewFavorite.animate().scaleX(1).scaleY(1).start();
                    item.setLiked(true);
                    mainPresenter.onButtonLikeClicked(item, true);
                }
            }
        });

        holder.viewHolderItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPresenter.onItemClick(item, position);
            }
        });

        holder.viewHolderProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPresenter.onProfileClick(item.getProfile());
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items, boolean clear) {
        if (clear)
        this.items.clear();

        this.items.addAll(items);
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
        @BindView(R.id.viewHolderProfile)
        public RelativeLayout viewHolderProfile;
        @BindView(R.id.viewHolderItem)
        public LinearLayout viewHolderItem;
        @BindView(R.id.buttonLike)
        public ImageView buttonLike;
        @BindView(R.id.imageViewFavorite)
        public ImageView imageViewFavorite;
        @BindView(R.id.textViewMeal)
        public TextView textViewMeal;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
