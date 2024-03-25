package com.itnation.foodmonkey.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.itnation.foodmonkey.Domain.Foods;
import com.itnation.foodmonkey.R;
import com.itnation.foodmonkey.databinding.ActivityFoodDetailsBinding;

public class FoodDetailsActivity extends AppCompatActivity {

    ActivityFoodDetailsBinding binding;

    Foods object;
    int num = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityFoodDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        getIntentExtra();
        setVariable();


    }//close onCreate----------------------

    private void setVariable() {

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        Glide.with(FoodDetailsActivity.this)
                .load(object.getImagePath())
                .into(binding.foodImgThumb);
        binding.foodPrice.setText("৳" + object.getPrice());
        binding.foodTitle.setText(object.getTitle());
        binding.detailsTxt.setText(object.getDescription());
        binding.ratingTxt.setText(object.getStar()+"Rating");
        binding.ratingBar.setRating((float) object.getStar());
        binding.totalPrice.setText(num * object.getPrice()+"$");


    }

    private void getIntentExtra() {

        object= (Foods) getIntent().getSerializableExtra("object");


    }


}