package com.itnation.foodmonkey.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.itnation.foodmonkey.Activity.FoodDetailsActivity;
import com.itnation.foodmonkey.Domain.Foods;
import com.itnation.foodmonkey.R;

import java.util.ArrayList;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.ViewHolder> {

    ArrayList<Foods> foodsArrayList;
    Context context;

    public FoodListAdapter(ArrayList<Foods> foodsArrayList, Context context) {
        this.foodsArrayList = foodsArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public FoodListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.food_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodListAdapter.ViewHolder holder, int position) {


        Foods foods = foodsArrayList.get(position);

        holder.titleText.setText(foods.getTitle());
        holder.starText.setText(""+foods.getStar());
        holder.priceText.setText("৳" + foods.getPrice());
        holder.timeText.setText(foods.getTimeValue()+"min");

        Glide.with(context)
                .load(foods.getImagePath()).
                transform(new CenterCrop(), new RoundedCorners(30)).
                into(holder.foodPic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(context, FoodDetailsActivity.class);
                intent.putExtra("object",foodsArrayList.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return foodsArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleText, starText, timeText, priceText;
        ImageView foodPic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleText = itemView.findViewById(R.id.title);
            starText = itemView.findViewById(R.id.star);
            timeText = itemView.findViewById(R.id.time);
            priceText = itemView.findViewById(R.id.price);
            foodPic = itemView.findViewById(R.id.foodImg);
        }
    }
}
