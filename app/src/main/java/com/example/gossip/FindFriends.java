package com.example.gossip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FindFriends extends AppCompatActivity {

    EditText EditText_SearchFriends;

    private ListView listView;
    DatabaseReference databaseReference;
    private List<User> userList;
    private CustomAdapterUserListForAdding customAdapterUserListForAdding;
    private String currentUserEmail=Homepage.firebaseUser.getEmail();

    Button buttonAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_friends);


        //for database connection
        databaseReference= FirebaseDatabase.getInstance("https://gossip-32bb8-default-rtdb.firebaseio.com/").getReference("Users");

        //getUsers();
        //checkFriends();
        userList=new ArrayList<>();
        customAdapterUserListForAdding=new CustomAdapterUserListForAdding(FindFriends.this,userList);
        listView=findViewById(R.id.ListViewUsers);
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Toast.makeText(this, "Asi ami", Toast.LENGTH_SHORT).show();
//                String tempListView=listViewItems[i].toString();
//                 View v=customAdapter.getView(i,view,listView);
//                 Intent intent=new Intent(ChildSpecialistUI.this,open_doctor.class);
////
//                 startActivity(intent);
            int itempos=i;
            //Toast.makeText(ChildSpecialistUI.this, "clicked position: "+itempos, Toast.LENGTH_LONG).show();
            User o= userList.get(itempos);
            //Toast.makeText(ChildSpecialistUI.this, "O: "+o, Toast.LENGTH_LONG).show();
            String name=o.getFullname();
            String email=o.getEmail();
            Toast.makeText(FindFriends.this, "Shob kichu nisi eibar pathabo", Toast.LENGTH_LONG).show();
            Intent intent=new Intent(FindFriends.this, OpenUser.class);
//                intent.putExtra("Name",name);
//                intent.putExtra("Mobile",mobile);
//                intent.putExtra("bmdc",bmdc);
//                intent.putExtra("email",email);
//                intent.putExtra("pass",pass);
            startActivity(intent);
        });

        EditText_SearchFriends=findViewById(R.id.EditText_SearchFriends);
        listView=findViewById(R.id.ListViewUsers);
        buttonAdd=findViewById(R.id.Button_addAsFriend);


    }

    private void checkFriends() {
    }

    private void getUsers() {

        //Image = (ImageView)findViewById(R.id.imageLogo);

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