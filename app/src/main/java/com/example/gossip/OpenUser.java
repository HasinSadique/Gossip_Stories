package com.example.gossip;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OpenUser extends AppCompatActivity {

    TextView username;
    FirebaseUser currrentUser= Login.getFirebaseUser();
    String friendUser;
    String Mykey = Homepage.UserKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_user);

        username=findViewById(R.id.TextView_OpenUser_username);

    }

    @Override
    protected void onStart() {
        username.setText(getIntent().getExtras().getString("UserName"));
        friendUser=getIntent().getExtras().getString("UserEmail");
        super.onStart();
    }

    public void goback(View view) {
        finish();
    }

    public void AddAsfriend(View view) {

        Toast.makeText(this, "Hobe add wait koro", Toast.LENGTH_LONG).show();
        String currentUserEmail = currrentUser.getEmail();
        String ToBeFriendEmail=friendUser;
        MyFriend myFriend = new MyFriend(currentUserEmail,ToBeFriendEmail);



    }

    public void AddfriendAnonymously(View view) {
        Toast.makeText(this, "Hobe hobe ei khelao hobe", Toast.LENGTH_LONG).show();
    }
}