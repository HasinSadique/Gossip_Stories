package com.example.gossip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FindFriends extends AppCompatActivity {

    EditText EditText_SearchFriends;

    private ListView listView;
    DatabaseReference databaseReference;
    private List<User> userList;
    private CustomAdapterUserListForAdding customAdapterUserListForAdding;
    private String currentUserEmail=Login.getFirebaseUser().getEmail();

    Button buttonAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_friends);

        EditText_SearchFriends=findViewById(R.id.EditText_SearchFriends);
        //for database connection
        databaseReference= FirebaseDatabase.getInstance("https://gossip-32bb8-default-rtdb.firebaseio.com/").getReference("Users");

        //getUsers();
        //checkFriends();
        userList=new ArrayList<>();
        customAdapterUserListForAdding=new CustomAdapterUserListForAdding(FindFriends.this,userList);
        listView=findViewById(R.id.ListViewUsers);
        Toast.makeText(FindFriends.this, "Heelllooo jee", Toast.LENGTH_SHORT).show();

        listView.setOnItemClickListener((parent, view, position, id) -> {
            int itempos=position;
            openUser(itempos);


            //Toast.makeText(ChildSpecialistUI.this, "clicked position: "+itempos, Toast.LENGTH_LONG).show();
            });

    }

    private void openUser(int itempos) {
        User o= userList.get(itempos);
        String name=o.getFullname();
        String email=o.getEmail();
        Toast.makeText(FindFriends.this, "Shob kichu nisi eibar pathabo", Toast.LENGTH_LONG).show();
        Intent intent=new Intent(FindFriends.this, OpenUser.class);
        intent.putExtra("UserName",name);
        intent.putExtra("UserEmail",email);
        startActivity(intent);



    }


    @Override
    protected void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userList.clear();
                for (DataSnapshot dataSnapshot1:  dataSnapshot.getChildren()){
                    User user=dataSnapshot1.getValue(User.class);
                    if(!user.getEmail().equals(currentUserEmail)){
                        userList.add(user);
                    }
                }
                listView.setAdapter(customAdapterUserListForAdding);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        super.onStart();

    }

    public void goback(View view) {
        finish();
    }
}