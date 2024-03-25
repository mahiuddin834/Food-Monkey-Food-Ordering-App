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
import com.itnation.foodmonkey.Activity.ListFoodActivity;
import com.itnation.foodmonkey.Domain.Category;
import com.itnation.foodmonkey.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    ArrayList<Category> categoryArrayList;
    Context context;

    public CategoryAdapter(ArrayList<Category> categoryArrayList) {
        this.categoryArrayList = categoryArrayList;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context= parent.getContext();

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {

        Category category = categoryArrayList.get(position);

        holder.categoryNameTxt.setText(category.getName());


        switch (position){

            case 0:{
                holder.categoryImg.setBackgroundResource(R.drawable.category1_background);
                break;
            }case 1:{
                holder.categoryImg.setBackgroundResource(R.drawable.category2_background);
                break;
            }case 2:{
                holder.categoryImg.setBackgroundResource(R.drawable.category3_background);
                break;
            }case 3:{
                holder.categoryImg.setBackgroundResource(R.drawable.category4_background);
                break;
            }case 4:{
                holder.categoryImg.setBackgroundResource(R.drawable.category5_background);
                break;
            }case 5:{
                holder.categoryImg.setBackgroundResource(R.drawable.category6_background);
                break;
            }case 6:{
                holder.categoryImg.setBackgroundResource(R.drawable.category7_background);
                break;
            }case 7:{
                holder.categoryImg.setBackgroundResource(R.drawable.category8_background);
                break;
            }
        }

        int drawableResourceId= context.getResources().getIdentifier(categoryArrayList.get(position).getImagePath(),"drawable",
                holder.itemView.getContext().getPackageName());

        Glide.with(context).load(drawableResourceId).into(holder.categoryImg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ListFoodActivity.class);
                intent.putExtra("categoryTd",category.getId());
                intent.putExtra("categoryName",category.getName());



                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView categoryImg;
        TextView categoryNameTxt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryImg=itemView.findViewById(R.id.categoryImg);
            categoryNameTxt=itemView.findViewById(R.id.categoryNameTxt);
        }
    }
}
