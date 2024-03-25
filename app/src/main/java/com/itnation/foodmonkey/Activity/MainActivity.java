package com.itnation.foodmonkey.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.itnation.foodmonkey.Adapter.BestFoodsAdapter;
import com.itnation.foodmonkey.Adapter.CategoryAdapter;
import com.itnation.foodmonkey.Domain.Category;
import com.itnation.foodmonkey.Domain.Foods;
import com.itnation.foodmonkey.Domain.Location;
import com.itnation.foodmonkey.Domain.Price;
import com.itnation.foodmonkey.Domain.Time;
import com.itnation.foodmonkey.R;
import com.itnation.foodmonkey.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        initLocation();
        initTime();
        initPrice();
        initBestFoods();
        initCategory();
        initVariable();

    }//---OnCreate End----------------

    private void initVariable() {

        binding.logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));

            }
        });


        binding.searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String searchTxt= binding.searchEditText.getText().toString();
                if (!searchTxt.isEmpty()){

                    Intent intent= new Intent(MainActivity.this, ListFoodActivity.class);
                    intent.putExtra("searchText", searchTxt);
                    intent.putExtra("isSearch", true);
                    startActivity(intent);
                }
            }
        });
    }



    private void initBestFoods() {

        DatabaseReference myRef = database.getReference("Foods");
        binding.progressBarBestFood.setVisibility(View.VISIBLE);

        ArrayList<Foods> bestFoodsArrayList= new ArrayList<>();
        Query query= myRef.orderByChild("BestFood").equalTo(true);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){
                    for (DataSnapshot issue : snapshot.getChildren()){
                        bestFoodsArrayList.add(issue.getValue(Foods.class));
                    }
                    if (bestFoodsArrayList.size()>0){

                        binding.recyclerViewBestFood.setLayoutManager(new LinearLayoutManager(MainActivity.this,
                                LinearLayoutManager.HORIZONTAL, false));
                        BestFoodsAdapter bestFoodsAdapter = new BestFoodsAdapter(bestFoodsArrayList);
                        binding.recyclerViewBestFood.setAdapter(bestFoodsAdapter);
                        bestFoodsAdapter.notifyDataSetChanged();
                    }

                    binding.progressBarBestFood.setVisibility(View.GONE);



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }






    private void initCategory() {

        DatabaseReference myRef = database.getReference("Category");
        binding.progressBarCategory.setVisibility(View.VISIBLE);

        ArrayList<Category> categoryArrayList= new ArrayList<>();
        //Query query= myRef.orderByChild("BestFood").equalTo(true);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){
                    for (DataSnapshot issue : snapshot.getChildren()){
                        categoryArrayList.add(issue.getValue(Category.class));
                    }
                    if (categoryArrayList.size()>0){

                        binding.recyclerViewCategory.setLayoutManager(new GridLayoutManager(MainActivity.this, 4));
                        CategoryAdapter categoryAdapter= new CategoryAdapter(categoryArrayList);
                        binding.recyclerViewCategory.setAdapter(categoryAdapter);
                        categoryAdapter.notifyDataSetChanged();
                    }

                    binding.progressBarCategory.setVisibility(View.GONE);



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



    private void initLocation() {

        DatabaseReference myRef= database.getReference("Location");
        ArrayList<Location> locationArrayList= new ArrayList<>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot issue: snapshot.getChildren()){

                        locationArrayList.add(issue.getValue(Location.class));
                    }

                    ArrayAdapter<Location> arrayAdapter = new ArrayAdapter<>(MainActivity.this, R.layout.spiner_item, locationArrayList);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.locationSp.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    private void initTime() {

        DatabaseReference myRef= database.getReference("Time");
        ArrayList<Time> timeArrayList= new ArrayList<>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot issue: snapshot.getChildren()){

                        timeArrayList.add(issue.getValue(Time.class));
                    }

                    ArrayAdapter<Time> arrayAdapter = new ArrayAdapter<>(MainActivity.this, R.layout.spiner_item, timeArrayList);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.timeSp.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    private void initPrice() {

        DatabaseReference myRef= database.getReference("Price");
        ArrayList<Price> priceArrayList= new ArrayList<>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot issue: snapshot.getChildren()){

                        priceArrayList.add(issue.getValue(Price.class));
                    }

                    ArrayAdapter<Price> arrayAdapter = new ArrayAdapter<>(MainActivity.this, R.layout.spiner_item, priceArrayList);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.priceSp.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


}