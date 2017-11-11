package com.coelho.estevao.tecnonutrifeed.presentation.ui.profile.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.coelho.estevao.tecnonutrifeed.R;
import com.coelho.estevao.tecnonutrifeed.domain.entity.Item;
import com.coelho.estevao.tecnonutrifeed.presentation.ui.profile.ProfilePresenter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by estevao on 06/11/17.
 */

public class MiniItemAdapter extends RecyclerView.Adapter<MiniItemAdapter.MyViewHolder> {

    List<Item> items;
    private ProfilePresenter profilePresenter;

    public MiniItemAdapter(ProfilePresenter profilePresenter) {
        this.profilePresenter = profilePresenter;
        this.items = new ArrayList<>();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_mini_item, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Item item = items.get(position);

        Picasso.with(holder.itemImage.getContext())
                .load(item.getImage()).placeholder(R.drawable.ic_local_pizza)
                .into(holder.itemImage);


        holder.itemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profilePresenter.onMiniItemClick(item);
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

        @BindView(R.id.itemImage)
        public ImageView itemImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
