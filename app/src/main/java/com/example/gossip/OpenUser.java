package com.example.gossip;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class OpenUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_user);
    }

    public void goback(View view) {
        finish();
    }

    public void AddAsfriend(View view) {
        Toast.makeText(this, "Hobe add wait koro", Toast.LENGTH_LONG).show();
    }

    public void AddfriendAnonymously(View view) {
        Toast.makeText(this, "Hobe hobe ei khelao hobe", Toast.LENGTH_LONG).show();
    }
}