package com.itnation.foodmonkey.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.itnation.foodmonkey.R;
import com.itnation.foodmonkey.databinding.ActivityCartBinding;

public class CartActivity extends AppCompatActivity {

    ActivityCartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



    }//close onCreate-------------------------------
}