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
import com.itnation.foodmonkey.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseActivity {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setVariable();



    }//----------close OnCreate----------------------------

    private void setVariable() {

        binding.signUpTxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });


        binding.logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = binding.emailEditText.getText().toString();
                String password= binding.passwordEditText.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()){

                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){

                                startActivity(new Intent(LoginActivity.this,MainActivity.class));

                            }else {

                                Toast.makeText(LoginActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();


                            }


                        }
                    });
                }else {

                    Toast.makeText(LoginActivity.this, "Please fill in the E-Mail and Password", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}