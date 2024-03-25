package com.itnation.foodmonkey.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.itnation.foodmonkey.R;
import com.itnation.foodmonkey.databinding.ActivitySignUpBinding;

public class SignUpActivity extends BaseActivity {

    ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setVariable();

    }//-------close onCreate ---------------

    private void setVariable() {

        binding.logInTxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        });

        binding.signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = binding.emailEditText.getText().toString();
                String password= binding.passwordEditText.getText().toString();

                if (password.length()<6){

                    Toast.makeText(SignUpActivity.this, "Your Password must be 6 character", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isComplete()){

                            startActivity(new Intent(SignUpActivity.this, MainActivity.class));


                        }else {

                            Toast.makeText(SignUpActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });


    }
}