package com.example.gossip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Registration extends AppCompatActivity {

    TextView FirstName,LastName;
    public static String fullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        FirstName=findViewById(R.id.EditText_firstName);
        LastName=findViewById(R.id.EditText_LastName);

        fullName = FirstName.getText().toString()+" "+LastName.getText().toString();

    }

    public void proceed(View view) {
        Intent intent = new Intent(this, Registration_part2.class);
        startActivity(intent);
    }


    public void back(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        this.finish();
    }
}