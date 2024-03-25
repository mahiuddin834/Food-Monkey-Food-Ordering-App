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

public class BestFoodsAdapter extends RecyclerView.Adapter<BestFoodsAdapter.ViewHolder> {

    ArrayList<Foods> bestFoodsArrayList;
    Context context;

    public BestFoodsAdapter(ArrayList<Foods> bestFoodsArrayList) {
        this.bestFoodsArrayList = bestFoodsArrayList;
    }

    @NonNull
    @Override
    public BestFoodsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context= parent.getContext();

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.best_foods_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BestFoodsAdapter.ViewHolder holder, int position) {

        Foods foods = bestFoodsArrayList.get(position);

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
                intent.putExtra("object",bestFoodsArrayList.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return bestFoodsArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleText, starText, timeText, plusText, priceText;
        ImageView foodPic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleText = itemView.findViewById(R.id.titleText);
            starText = itemView.findViewById(R.id.starText);
            timeText = itemView.findViewById(R.id.timeText);
            plusText = itemView.findViewById(R.id.plusText);
            priceText = itemView.findViewById(R.id.priceText);
            foodPic = itemView.findViewById(R.id.foodPic);
        }
    }
}
