package com.example.gossip;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MyProfile extends AppCompatActivity {

    private String UID,FullName,Email,phone,Birthday;
    TextView name,email,bio,mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        //GetUserDetails
        UID=Homepage.firebaseUser.getUid();
        FullName=Homepage.getFullname();
        Email=Homepage.getEmail();
        Birthday=Homepage.getBirthday();

        name=findViewById(R.id.TextView_MyProfile_Name);
        email=findViewById(R.id.TextView_MyProfile_Email);
        bio=findViewById(R.id.TextView_MyProfile_BIO);
        mobile=findViewById(R.id.TextView_MyProfile_Mobile);

        name.setText("  Name\n  "+FullName);
        email.setText("  "+Email);
//        bio.setText("Bio\n  "+);
//        mobile.setText();





    }

    public void CangeProPic(View view) {
        //Access gallery and select photo

    }

    public void goback(View view) {
        this.finish();
    }
}