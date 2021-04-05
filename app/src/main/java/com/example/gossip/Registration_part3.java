package com.example.gossip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Registration_part3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_part3);
    }

    public void Register(View view) {
        Intent intent=new Intent(this, Homepage.class);
        startActivity(intent);
        this.finish();
    }
}