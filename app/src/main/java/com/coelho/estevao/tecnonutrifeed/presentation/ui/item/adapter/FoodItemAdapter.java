package com.coelho.estevao.tecnonutrifeed.presentation.ui.item.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coelho.estevao.tecnonutrifeed.R;
import com.coelho.estevao.tecnonutrifeed.domain.entity.Food;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by estevao on 06/11/17.
 */

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.MyViewHolder> {

    List<Food> foodList;

    public FoodItemAdapter() {
        this.foodList = new ArrayList<>();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Food food = foodList.get(position);

        holder.textViewFoodName.setText(food.getDescription());
        holder.textViewUnit.setText(String.format("%d (%s)", food.getAmount(), food.getMeasure()));
        holder.textViewCalValue.setText(food.getEnergy() + " Kcal");
        holder.textViewGordValue.setText(food.getFat() + " g");
        holder.textViewProtValue.setText(food.getFat() + " g");
        holder.textViewCarbValue.setText(food.getCarbohydrate() + " g");

    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public List<Food> getItems() {
        return foodList;
    }

    public void setItems(List<Food> foodList) {
        this.foodList.clear();
        this.foodList.addAll(foodList);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textViewFoodName)
        public TextView textViewFoodName;
        @BindView(R.id.textViewUnit)
        public TextView textViewUnit;
        @BindView(R.id.textViewCalValue)
        public TextView textViewCalValue;
        @BindView(R.id.textViewGordValue)
        public TextView textViewGordValue;
        @BindView(R.id.textViewProtValue)
        public TextView textViewProtValue;
        @BindView(R.id.textViewCarbValue)
        public TextView textViewCarbValue;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
