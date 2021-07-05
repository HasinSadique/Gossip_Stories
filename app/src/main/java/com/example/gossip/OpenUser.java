package com.example.gossip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class OpenUser extends AppCompatActivity {

    TextView username;
    String currrentUserUID = Login.getFirebaseUser().getUid();
    String friendUserUID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_user);

        username = findViewById(R.id.TextView_OpenUser_username);

    }

    @Override
    protected void onStart() {
        username.setText(getIntent().getExtras().getString("UserName"));

        friendUserUID = getIntent().getExtras().getString("UID");
        super.onStart();
    }

    public void goback(View view) {
        finish();
    }

    public void AddAsfriend(View view) {

        Toast.makeText(this, "Hobe add wait koro", Toast.LENGTH_LONG).show();
        final boolean[] X = {false};
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> Request = new HashMap<>();
        Request.put("User", currrentUserUID);
        Request.put("SentTo", friendUserUID);

        db.collection("Friend_Requests").add(Request).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                X[0] =true;
                Toast.makeText(OpenUser.this, "DRf\n\n"+documentReference, Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                X[0] =false;
                Toast.makeText(OpenUser.this, "Exception=>\n\n"+e, Toast.LENGTH_SHORT).show();
            }
        });


//        MyFriend myFriend = new MyFriend(currrentUserUID,friendUserUID);


    }

    public void AddfriendAnonymously(View view) {
        Toast.makeText(this, "Hobe hobe ei khelao hobe", Toast.LENGTH_LONG).show();
    }
}