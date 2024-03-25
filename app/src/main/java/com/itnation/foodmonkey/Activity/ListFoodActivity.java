package com.itnation.foodmonkey.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.itnation.foodmonkey.Adapter.FoodListAdapter;
import com.itnation.foodmonkey.Domain.Foods;
import com.itnation.foodmonkey.R;
import com.itnation.foodmonkey.databinding.ActivityListFoodBinding;

import java.util.ArrayList;

public class ListFoodActivity extends BaseActivity {

    ActivityListFoodBinding binding;

    int categoryId;
    String categoryName;
    String searchText;
    private boolean isSearch;
    FoodListAdapter foodListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityListFoodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        getStringExtra();
        initFoodList();
    }//=====close onCreate ==========

    private void initFoodList() {

        DatabaseReference myRef = database.getReference("Foods");
        binding.progressBarFoodList.setVisibility(View.VISIBLE);
        ArrayList<Foods>foodsArrayList = new ArrayList<>();
        foodListAdapter= new FoodListAdapter(foodsArrayList, ListFoodActivity.this);

        Query query;
        if (isSearch){
            query= myRef.orderByChild("Title").startAt(searchText+ '\uf8ff');

        }else {
            query= myRef.orderByChild("CategoryId").equalTo(categoryId);

        }

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){

                    for (DataSnapshot issue : snapshot.getChildren()){

                        foodsArrayList.add(issue.getValue(Foods.class));
                    }
                    if (foodsArrayList.size()>0){
                        binding.foodListRecyclerView.setLayoutManager( new GridLayoutManager(ListFoodActivity.this,2));
                        binding.foodListRecyclerView.setAdapter(foodListAdapter);

                    }
                    binding.progressBarFoodList.setVisibility(View.GONE);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getStringExtra() {

        categoryId = getIntent().getIntExtra("categoryTd",0);
        categoryName = getIntent().getStringExtra("categoryName");
        searchText= getIntent().getStringExtra("searchText");
        isSearch = getIntent().getBooleanExtra("isSearch", false);

        binding.foodListTittle.setText(categoryName);
        binding.foodListBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });



    }


}