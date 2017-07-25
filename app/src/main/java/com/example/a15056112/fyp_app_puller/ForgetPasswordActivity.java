package com.example.a15056112.fyp_app_puller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText mEmail;
    private Button mBtnForgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        mAuth = FirebaseAuth.getInstance();

        mEmail = (EditText)findViewById(R.id.forgetEmail);
        mBtnForgot = (Button)findViewById(R.id.forgetBtn);

        mBtnForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                mAuth.sendPasswordResetEmail(email);
            }
        });
    }
}
